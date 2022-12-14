package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.Pagination;
import com.goodkin.model.menu.MainMenu;
import com.goodkin.model.menu.Menu;
import com.goodkin.model.menu.MenuCategroy;
import com.goodkin.model.menu.NewMenu;

@Mapper
public interface MenuRepository {

    // 메뉴
    public List<Menu> getMenus();

    public Menu getMenu(Long MenuNo);

    public List<Menu> findByCategory(MenuCategroy category);
    
    public Menu findByName(String name);

    public List<Menu> findAllByNewMenu();

    public int save(Menu Menu);

    public int update(Menu Menu);

    public int delete(Long MenuNo);

    //메인 메뉴 리스트
    public List<MainMenu> getMainMenus();

    //관리자 메뉴 리스트
    public int listCount(@Param("kind")String kind, @Param("keyword")String keyword, @Param("category") String category);

    public List<Menu> list(@Param("kind")String kind, @Param("keyword")String keyword, @Param("category") String category, @Param("paging") Pagination paging);

    //메뉴 현재이름을 제외한 이름 중복확인
    public Menu menuNameValidation(@Param("menuNo") Long menuNo, @Param("newName") String name);

    // 해당 카테고리 메인메뉴 갯수 
    public int getMainMenusCount(MenuCategroy category);  

    // 메인메뉴 설정
    public int mainMenuInsert(Long menuNo);

    // 메인메뉴 취소
    public int mainMenuDelete(Long menuNo);

    // 메인메뉴 리스트
    public List<MainMenu> getMainMenuList();

    // 메인메뉴 전체 정렬 업데이트
    public void mainMenuUpdateAll(@Param("newMainMenus") List<MainMenu> newMainMenus);

    // 업
    public void mainMenuSortUp(Long mainMenuNo);

    // 다운
    public void mainMenuSortDown(Long mainMenuNo);

    // 신메뉴 설정
    public List<NewMenu> newMenuList();
    public NewMenu getNewMenu(Long newMenuNo);
    public int newMenuInsert(NewMenu newMenu);
    public int newMenuUpdate(NewMenu newMenu);
    public int newMenuDelete(Long newMenuNo);
    

}
