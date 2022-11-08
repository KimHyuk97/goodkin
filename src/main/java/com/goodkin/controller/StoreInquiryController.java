package com.goodkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.store.StoreInquiry;
import com.goodkin.service.StoreInquiryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreInquiryController {

    private final StoreInquiryService storeInquiryService;

    @GetMapping("/admin/storeInquiry/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "customer_name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String address,
        @RequestParam(required = false, defaultValue = "") String subAddress) {

        return storeInquiryService.list(mv, kind, keyword, page, address, subAddress);
    }

    @GetMapping("/admin/storeInquiry/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long storeInquiryNo) {

        return storeInquiryService.form(mv, mode, storeInquiryNo);
    }

    @PostMapping("/api/storeInquiry/insert")
    @ResponseBody
    public ResponseDto<?> insert(@RequestBody StoreInquiry storeInquiry) {

        return storeInquiryService.insert(storeInquiry);
    }

    @PostMapping("/admin/storeInquiry/update")
    @ResponseBody
    public ResponseDto<?> update(@RequestBody StoreInquiry storeInquiry) {

        return storeInquiryService.update(storeInquiry);
    }

    @PostMapping("/admin/storeInquiry/delete/{storeInquiryNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long storeInquiryNo) {

        return storeInquiryService.delete(storeInquiryNo);
    }

    
}
