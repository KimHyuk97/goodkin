package com.goodkin.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.Pagination;
import com.goodkin.model.menu.Menu;
import com.goodkin.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

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


    
}
