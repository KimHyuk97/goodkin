package com.goodkin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodkin.model.ResponseDto;
import com.goodkin.service.PageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/page")
public class PageController {

    private final PageService pageService;

    /*
     * 페이지 
     */
    @GetMapping("/form")
    public String form() {
        return "admin/page/form";
    }

    /*
     * get 페이지
     */
    @PostMapping("/getPage/{category}")
    @ResponseBody
    public ResponseDto<?> getPage(@PathVariable String category) {
        return pageService.getPage(category);
    }

    /*
     * 페이지 사진 수정
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseDto<?> update(Long pageId,
        @RequestPart List<MultipartFile> files) {
        return pageService.update(pageId, files);
    }

    
}
