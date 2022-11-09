package com.goodkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodkin.repository.MenuRepository;
import com.goodkin.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    
    @GetMapping({"/", ""})
    public String home(Model model) {
        model.addAttribute("menus", menuRepository.getMainMenus());
        model.addAttribute("reviews", reviewRepository.getReviews());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "html/about";
    }

    @GetMapping("/self-employment")
    public String selfEmployment() {
        return "html/self-employment";
    }

    @GetMapping("/strategy")
    public String strategy() {
        return "html/strategy";
    }

    @GetMapping("/franchise")
    public String franchise() {
        return "html/franchise";
    }

    @GetMapping("/menu")
    public String menu() {
        return "html/menu";
    }

    @GetMapping("/store")
    public String store() {
        return "html/store";
    }

    @GetMapping("/customer-inquiry")
    public String customerInquiry() {
        return "html/customer-inquiry";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "html/privacy";
    }

}