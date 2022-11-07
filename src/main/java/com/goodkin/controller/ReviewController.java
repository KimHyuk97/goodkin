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
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/review")
public class ReviewController {
    private final ReviewService reviewService;
    
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {

        return reviewService.list(mv);
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseDto<?> insert(@RequestPart List<MultipartFile> files) {

        return reviewService.insert(files);
    }

    @PostMapping("/delete/{reviewNo}")
    @ResponseBody
    public ResponseDto<?> delete(@PathVariable Long reviewNo) {

        return reviewService.delete(reviewNo);
    }


}
