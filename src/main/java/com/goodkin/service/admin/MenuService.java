package com.goodkin.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.Pagination;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.menu.Menu;
import com.goodkin.repository.MenuRepository;
import com.goodkin.service.ResponseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

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

        if(menuNo != null) {
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
        if(validNameMenu != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        // 파일 업로드
        if(files != null && !files.isEmpty()) {
            fileUpload(files, menu);
        }

        System.err.println("저장할 메뉴 :"+menu);

        // 메뉴 저장
        int insert = menuRepository.save(menu);

        return responseService.responseBuilder(insert > 0 ? "등록되었습니다." : "등록 실패하였습니다.", null);
    }

    @Transactional
    public ResponseDto<?> update(Menu menu, List<MultipartFile> files) {
        Menu validNamemenu = menuRepository.menuNameValidation(menu.getMenuNo(), menu.getName());

        if(validNamemenu != null) {
            return responseService.responseBuilder("이미 등록된 가맹점 입니다.", null);
        }

        int update = menuRepository.update(menu);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    public ResponseDto<?> delete(Long menuNo) {
        int delete = menuRepository.delete(menuNo);

        return responseService.responseBuilder(delete > 0 ? "삭제되었습니다." : "삭제 실패하였습니다.", null);
    }


    private void fileUpload(List<MultipartFile> files, Menu menu) {
        String path = "/www/menu/";
    
        try {
            List<String> fileNames = ftp.uploadFile(files, path);;
            if(!fileNames.isEmpty()) {
                menu.setFile(fileNames.get(0));
                menu.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/menu/"+fileNames.get(0));
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
    
}
