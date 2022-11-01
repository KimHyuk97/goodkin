package com.goodkin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.store.Store;
import com.goodkin.service.admin.StoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/store")
public class StoreController {

    private final StoreService storeService;
    
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String address,
        @RequestParam(required = false, defaultValue = "") String subAddress) {

        return storeService.list(mv, kind, keyword, page, address, subAddress);
    }

    @GetMapping("/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long storeNo) {

        return storeService.form(mv, mode, storeNo);
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseDto<?> insert(@RequestBody Store store) {

        return storeService.insert(store);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseDto<?> update(@RequestBody Store store) {

        return storeService.update(store);
    }

    @PostMapping("/delete/{storeNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long storeNo) {

        return storeService.delete(storeNo);
    }
    
}
