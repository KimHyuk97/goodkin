package com.goodkin.service;

import org.springframework.stereotype.Service;

import com.goodkin.model.Admin;
import com.goodkin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin getAdmin(Long adminNo) {
        return adminRepository.getAdmin(adminNo);
    }
}
