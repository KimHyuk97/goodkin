package com.goodkin.controller.admin;

import java.util.Collections;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodkin.auth.AdminDetails;
import com.goodkin.model.DelModel;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.admin.Admin;
import com.goodkin.repository.AdminRepository;
import com.goodkin.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @GetMapping({"", "/"})
    public String home(@AuthenticationPrincipal AdminDetails admin) {
        if(admin != null) {
            return "redirect:/admin/store/list";
        }

        return "admin/index";
    }

    @GetMapping("/login/failure")
    public String adminLoginError(Model model) {
        model.addAttribute("msg", "존재하지 않는 아이디 또는 비밀번호 입니다.");
        return "admin/index";
    }

    @PostMapping("/utils/mutldel")
	@ResponseBody
	public ResponseDto<?> mutldel(@RequestBody DelModel delModel) {
		adminRepository.mutldel(delModel);

		return ResponseDto.builder()
                        .message("삭제되었습니다.")
                        .result(Collections.emptyList())
                        .build();
	}




    @GetMapping("/getAdmin/{adminNo}")
    public Admin getAdmin(@PathVariable Long adminNo) {
        return adminService.getAdmin(adminNo);
    }
}
