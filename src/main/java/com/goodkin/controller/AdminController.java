package com.goodkin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodkin.model.Admin;
import com.goodkin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getAdmin/{adminNo}")
    public Admin getAdmin(@PathVariable Long adminNo) {
        return adminService.getAdmin(adminNo);
    }
}
