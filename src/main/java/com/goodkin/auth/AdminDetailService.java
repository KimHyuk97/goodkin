package com.goodkin.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goodkin.model.admin.Admin;
import com.goodkin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Admin admin = adminRepository.findById(id);

        if(admin == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+id);
        }
           
        return new AdminDetails(admin);
    }    
}
