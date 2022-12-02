package com.goodkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodkin.model.site.PolicyType;
import com.goodkin.repository.MenuRepository;
import com.goodkin.repository.PageRepository;
import com.goodkin.repository.PolicyRepository;
import com.goodkin.repository.PopUpRepository;
import com.goodkin.repository.ReviewRepository;
import com.goodkin.repository.SiteRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final PolicyRepository policyRepository;
    private final PopUpRepository popUpRepository;
        
    @GetMapping({"/", ""})
    public String home(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("menus", menuRepository.getMainMenus());
        model.addAttribute("reviews", reviewRepository.getReviews());
        model.addAttribute("section1", pageRepository.getPages("main_page", 1));
        model.addAttribute("section2", pageRepository.getPages("main_page", 2));
        model.addAttribute("section3", pageRepository.getPages("main_page", 3));
        model.addAttribute("popup", popUpRepository.getPopUpUrl());
        
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("section1", pageRepository.getPages("about", 1));
        model.addAttribute("section2", pageRepository.getPages("about", 2));
        model.addAttribute("section3", pageRepository.getPages("about", 3));
        model.addAttribute("section4", pageRepository.getPages("about", 4));
        return "html/about";
    }

    @GetMapping("/self-employment")
    public String selfEmployment(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("section1", pageRepository.getPages("self_employment", 1));
        model.addAttribute("section2", pageRepository.getPages("self_employment", 2));
        model.addAttribute("section3", pageRepository.getPages("self_employment", 3));
        return "html/self-employment";
    }

    @GetMapping("/strategy")
    public String strategy(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("section1", pageRepository.getPages("strategy", 1));
        model.addAttribute("section2", pageRepository.getPages("strategy", 2));
        model.addAttribute("section3", pageRepository.getPages("strategy", 3));
        model.addAttribute("section4", pageRepository.getPages("strategy", 4));
        model.addAttribute("section5", pageRepository.getPages("strategy", 5));
        return "html/strategy";
    }

    @GetMapping("/franchise")
    public String franchise(Model model) {
        model.addAttribute("privacy", policyRepository.getPolicy(PolicyType.PRIVACY));
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("section1", pageRepository.getPages("franchise", 1));
        return "html/franchise";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        model.addAttribute("section1", pageRepository.getPages("menu", 1));
        return "html/menu";
    }

    @GetMapping("/store")
    public String store(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        return "html/store";
    }

    @GetMapping("/customer-inquiry")
    public String customerInquiry(Model model) {
        model.addAttribute("site", siteRepository.getSite());
        return "html/customer-inquiry";
    }

    @GetMapping("/privacy")
    public String privacy(Model model) {
        model.addAttribute("privacy", policyRepository.getPolicy(PolicyType.PRIVACY));
        model.addAttribute("site", siteRepository.getSite());
        return "html/privacy";
    }

}
