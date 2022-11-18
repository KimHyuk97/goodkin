package com.goodkin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodkin.repository.MenuRepository;
import com.goodkin.repository.ReviewRepository;
import com.goodkin.repository.SiteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final SiteRepository siteRepository;
    private final HttpSession session;
    
    @GetMapping({"/", ""})
    public String home(Model model) {
        if(session.getAttribute("site") == null) {
            session.setAttribute("site", siteRepository.getSite());
        }

        model.addAttribute("menus", menuRepository.getMainMenus());
        model.addAttribute("reviews", reviewRepository.getReviews());
        
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
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
