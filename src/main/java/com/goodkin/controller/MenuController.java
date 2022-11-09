package com.goodkin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.menu.Menu;
import com.goodkin.model.menu.MenuCategroy;
import com.goodkin.repository.MenuRepository;
import com.goodkin.service.MenuService;
import com.goodkin.service.ResponseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final ResponseService responseService;

    @PostMapping("/api/menu/{category}")
    @ResponseBody
    public ResponseDto<?> getMenus(@PathVariable MenuCategroy category) {
        return responseService.responseBuilder("",
            menuRepository.findByCategory(category) );
    }
    
    @GetMapping("/admin/menu/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String category) {

        return menuService.list(mv, kind, keyword, page, category);
    }

    @GetMapping("/admin/menu/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long menuNo) {

        return menuService.form(mv, mode, menuNo);
    }

    @PostMapping("/admin/menu/insert")
    @ResponseBody
    public ResponseDto<?> insert(@ModelAttribute Menu menu,
        @RequestPart(required=false) List<MultipartFile> files) {

        return menuService.insert(menu, files);
    }

    @PostMapping("/admin/menu/update")
    @ResponseBody
    public ResponseDto<?> update(@ModelAttribute Menu menu,
        @RequestPart(required=false) List<MultipartFile> files) {

        return menuService.update(menu, files);
    }

    @PostMapping("/admin/menu/delete/{menuNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long menuNo) {

        return menuService.delete(menuNo);
    }
}
