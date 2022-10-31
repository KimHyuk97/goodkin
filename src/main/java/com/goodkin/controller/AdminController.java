package com.goodkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.admin.Admin;
import com.goodkin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping({"", "/"})
    public ModelAndView home(ModelAndView mv) {
        mv.setViewName("admin/index");
        return mv;
    }

    @GetMapping("/getAdmin/{adminNo}")
    public Admin getAdmin(@PathVariable Long adminNo) {
        return adminService.getAdmin(adminNo);
    }
}
