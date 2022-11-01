package com.goodkin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.service.admin.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/review")
public class ReviewController {
    private final ReviewService reviewService;
    
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv,
        @RequestParam(required = false) String kind,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false) String address) {

        return reviewService.list(mv, kind, keyword, page, address);
    }
}
