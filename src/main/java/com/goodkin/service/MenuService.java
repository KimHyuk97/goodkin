package com.goodkin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.Pagination;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.menu.MainMenu;
import com.goodkin.model.menu.Menu;
import com.goodkin.model.menu.MenuCategroy;
import com.goodkin.model.menu.NewMenu;
import com.goodkin.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

    // 메인 메뉴 리스트
    public ModelAndView menuMainList(ModelAndView mv) {

        List<MainMenu> list = menuRepository.getMainMenuList();

        mv.addObject("list", list);
        mv.setViewName("admin/menu/main/list");
        return mv;
    }

    // 메인 리스트
    public ModelAndView list(ModelAndView mv, String kind, String keyword, int page, String category) {
        int count = menuRepository.listCount(kind, keyword, category);

        Pagination paging = new Pagination(page, count);

        List<Menu> list = menuRepository.list(kind, keyword, category, paging);

        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("page", page);
        mv.addObject("kind", kind);
        mv.addObject("keyword", keyword);
        mv.addObject("category", category);
        mv.setViewName("admin/menu/list");
        return mv;
    }

    public ModelAndView form(ModelAndView mv, String mode, Long menuNo) {
        Menu menu = new Menu();

        if (menuNo != null) {
            menu = menuRepository.getMenu(menuNo);
        }

        mv.addObject("menu", menu);
        mv.addObject("mode", mode);
        mv.setViewName("admin/menu/form");
        return mv;
    }

    @Transactional
    public ResponseDto<?> insert(Menu menu, List<MultipartFile> files) {

        // 메뉴 이름 중복확인
        Menu validNameMenu = menuRepository.findByName(menu.getName());
        if (validNameMenu != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        // 파일 업로드
        if (files != null && !files.isEmpty()) {
            fileUpload(files, menu);
        }

        // 메뉴 저장
        int insert = menuRepository.save(menu);

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }

    @Transactional
    public ResponseDto<?> update(Menu menu, List<MultipartFile> files, List<MultipartFile> mainfiles) {
        Menu validNamemenu = menuRepository.menuNameValidation(menu.getMenuNo(), menu.getName());

        if (validNamemenu != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        // 파일 업로드
        if (files != null && !files.isEmpty()) {
            if (menu.getFile() != null) {
                filedelete(Arrays.asList(menu.getFile()));
            }
                
            fileUpload(files, menu);
        }

        // 메인 파일 업로드
        if (mainfiles != null && !mainfiles.isEmpty()) {
            if (menu.getMainFile() != null) {
                mainfiledelete(Arrays.asList(menu.getMainFile()));
            }

            mainfileUpload(mainfiles, menu);
        }

        int update = menuRepository.update(menu);

        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    @Transactional
    public ResponseDto<?> delete(Long menuNo) {

        Menu menu = menuRepository.getMenu(menuNo);
        if (menu != null && menu.getFile() != null) {
            filedelete(Arrays.asList(menu.getFile()));
        }

        int delete = menuRepository.delete(menuNo);

        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }

    /*
     * 메인 메뉴 설정
     */
    public ResponseDto<?> menuMainInsert(MenuCategroy category, Long menuNo) {

        String kind = category == MenuCategroy.CHICKEN ? "치킨메뉴" : "떡볶이메뉴";
        int maxCount = category == MenuCategroy.CHICKEN ? 5 : 2;
        int count = menuRepository.getMainMenusCount(category);

        if (count == maxCount) {
            return responseService.responseBuilder(kind + "는 최대 " + maxCount + "개로 제한되어 있습니다.", null);
        }

        menuRepository.mainMenuInsert(menuNo);

        return responseService.responseBuilder("해당 메뉴를 대표메뉴로 설정하였습니다.", null);
    }

    /*
     * 대표 메뉴 삭제
     */
    public ResponseDto<?> menuMainDelete(Long menuNo) {

        // 대표 메뉴 삭제
        menuRepository.mainMenuDelete(menuNo);

        // 정렬 초기화
        mainMenuSortInit();

        return responseService.responseBuilder("해당 메뉴를 대표메뉴에서 제외하였습니다.", null);
    }

    /*
     * 대표 메뉴 멀티 삭제
     */
    public ResponseDto<?> menuMainMultiDelete(List<Long> menuNos) {
        // 대표 메뉴 삭제
        for (Long menuNo : menuNos) {
            menuRepository.mainMenuDelete(menuNo);
        }

        // 정렬 초기화
        mainMenuSortInit();

        return responseService.responseBuilder("선택하신 메뉴를 대표메뉴에서 제외하였습니다.", null);
    }

    /*
     * 메인 메뉴 정렬 설정
     */
    public ResponseDto<?> mainMenuSortChange(Long[] ids) {

        List<MainMenu> newMainMenus = new ArrayList<>();

        if (ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setMainMenuNo(ids[i]);
                mainMenu.setSort(i + 1);
                newMainMenus.add(mainMenu);
            }
        }

        menuRepository.mainMenuUpdateAll(newMainMenus);

        return responseService.responseBuilder("순서를 변경하였습니다.", null);
    }

    // 정렬 초기화
    private void mainMenuSortInit() {
        List<MainMenu> newMainMenus = new ArrayList<>();

        List<MainMenu> mainMenus = menuRepository.getMainMenuList();

        if (!mainMenus.isEmpty()) {
            IntStream.range(0, mainMenus.size())
                    .forEach(i -> {
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.setMainMenuNo(mainMenus.get(i).getMainMenuNo());
                        mainMenu.setSort(i + 1);
                        newMainMenus.add(mainMenu);
                    });
        }

        menuRepository.mainMenuUpdateAll(newMainMenus);
    }


    private void fileUpload(List<MultipartFile> files, Menu menu) {
        String path = "/www/menu/";

        try {
            List<String> fileNames = ftp.uploadFile(files, path);
            ;
            if (!fileNames.isEmpty()) {
                menu.setFile(fileNames.get(0));
                menu.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/menu/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filedelete(List<String> files) {
        String path = "/www/menu/";

        try {
            ftp.deleteFile(files, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mainfileUpload(List<MultipartFile> files, Menu menu) {
        String path = "/www/menu/main/";

        try {
            List<String> fileNames = ftp.uploadFile(files, path);
            ;
            if (!fileNames.isEmpty()) {
                menu.setMainFile(fileNames.get(0));
                menu.setMainFileUrl("https://joeunfc2022.cdn1.cafe24.com/menu/main/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mainfiledelete(List<String> files) {
        String path = "/www/menu/main/";

        try {
            ftp.deleteFile(files, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 신메뉴 리스트
     */
    public ModelAndView newMenuList(ModelAndView mv) {

        NewMenu newMenu = menuRepository.getNewMenu(1L);

        mv.addObject("item", newMenu != null ? newMenu : new NewMenu());
        mv.setViewName("admin/menu/new/list");
        return mv;
    }

    /*
     * 신메뉴 등록
     */
    public ResponseDto<?> newMenuinsert(List<MultipartFile> files) {

        NewMenu newMenu = new NewMenu();

        try {
            List<String> fileNames = ftp.uploadFile(files, "/www/menu/new/");
            if (!fileNames.isEmpty()) {
                newMenu.setFileName(fileNames.get(0));
                newMenu.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/menu/new/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuRepository.newMenuInsert(newMenu);
        return responseService.responseBuilder("등록되었습니다.", null);
    }

     /*
     * 신메뉴 수정
     */
    public ResponseDto<?> newMenuUpdate(List<MultipartFile> files) {
        
        NewMenu newMenu = menuRepository.getNewMenu(1L);

        // 사진 삭제
        try {
            if(newMenu != null) {
                ftp.deleteFile(Arrays.asList(newMenu.getFileName()), 
                    "/www/menu/new/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 사진등록
        try {
            List<String> fileNames = ftp.uploadFile(files, "/www/menu/new/");
            if (!fileNames.isEmpty() && newMenu != null) {
                newMenu.setFileName(fileNames.get(0));
                newMenu.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/menu/new/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(newMenu != null) newMenu.setNewMenuNo(1L);
        menuRepository.newMenuUpdate(newMenu);
        return responseService.responseBuilder("등록되었습니다.", null);
    }

}
