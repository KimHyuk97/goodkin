package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.Pagination;
import com.goodkin.model.menu.MainMenu;
import com.goodkin.model.menu.Menu;
import com.goodkin.model.menu.MenuCategroy;

@Mapper
public interface MenuRepository {

    // 메뉴
    public List<Menu> getMenus();

    public Menu getMenu(Long MenuNo);

    public Menu findByCategory(MenuCategroy category);
    
    public Menu findByName(String name);

    public List<Menu> findAllByNewMenu();

    public int save(Menu Menu);

    public int update(Long MenuNo);

    public int delete(Long MenuNo);

    //메인 메뉴 리스트
    public List<MainMenu> getMainMenus();

    //관리자 메뉴 리스트
    public int listCount(@Param("kind")String kind, @Param("keyword")String keyword, @Param("category") String category);

    public List<Menu> list(@Param("kind")String kind, @Param("keyword")String keyword, @Param("category") String category, @Param("paging") Pagination paging);    
}
