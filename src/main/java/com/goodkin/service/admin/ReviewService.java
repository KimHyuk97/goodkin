package com.goodkin.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    public ModelAndView list(ModelAndView mv, String kind, String keyword, int page, String address) {
        return null;
    }


}
