package com.goodkin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.customerInquiry.CustomerInquiry;
import com.goodkin.service.CustomerInquiryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerInquiryController {

    private final CustomerInquiryService customerInquiryService;

    @PostMapping("/api/customerInquiry/insert")
    @ResponseBody
    public ResponseDto<?> insert(CustomerInquiry customerInquiry,
        @RequestPart(required=false) List<MultipartFile> customerInquiryFiles) {

        return customerInquiryService.insert(customerInquiry, customerInquiryFiles);
    }
    
    @GetMapping("/admin/customerInquiry/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false, defaultValue = "name") String kind,
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "") String type) {

        return customerInquiryService.list(mv, kind, keyword, page, type);
    }

    @GetMapping("/admin/customerInquiry/form")
    public ModelAndView form(ModelAndView mv, @RequestParam String mode,
                             @RequestParam(required = false) Long customerInquiryNo) {

        return customerInquiryService.form(mv, mode, customerInquiryNo);
    }

    @PostMapping("/admin/customerInquiry/update")
    @ResponseBody
    public ResponseDto<?> update(CustomerInquiry customerInquiry,
        @RequestPart(required=false) List<MultipartFile> customerInquiryFiles) {

        return customerInquiryService.update(customerInquiry, customerInquiryFiles);
    }

    @PostMapping("/admin/customerInquiry/deleteFile/{fileNo}")
    @ResponseBody
    public ResponseDto<?> deleteFile(@PathVariable Long fileNo) {

        return customerInquiryService.deleteFile(fileNo);
    }

    @PostMapping("/admin/customerInquiry/delete/{customerInquiryNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long customerInquiryNo) {

        return customerInquiryService.delete(customerInquiryNo);
    }
    
}
