package com.goodkin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.admin.Admin;
import com.goodkin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ResponseService responseService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Admin getAdmin(Long adminNo) {
        return adminRepository.getAdmin(adminNo);
    }

    /*
     * 관리자 마이페이지
     */
    public ModelAndView mypage(ModelAndView mv, Admin admin) {
        mv.addObject("admin", adminRepository.getAdmin(admin.getAdminNo()));
        mv.setViewName("admin/mypage/form");
        return mv;
    }

    /*
     * 관리자 수정
     */
    @Transactional
    public ResponseDto<?> adminUpdate(Admin admin) {

        if(admin.getPassword() != null) {
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        }

        int update = adminRepository.update(admin);

        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }
    
}
