package com.goodkin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.menu.Menu;
import com.goodkin.service.MenuService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/menu")
public class MenuController {

    private final MenuService menuService;
    
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String category) {

        return menuService.list(mv, kind, keyword, page, category);
    }

    @GetMapping("/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long menuNo) {

        return menuService.form(mv, mode, menuNo);
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseDto<?> insert(@ModelAttribute Menu menu,
        @RequestPart(required=false) List<MultipartFile> files) {

        return menuService.insert(menu, files);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseDto<?> update(@ModelAttribute Menu menu,
        @RequestPart(required=false) List<MultipartFile> files) {

        return menuService.update(menu, files);
    }

    @PostMapping("/delete/{menuNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long menuNo) {

        return menuService.delete(menuNo);
    }
}