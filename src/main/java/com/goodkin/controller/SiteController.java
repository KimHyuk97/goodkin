package com.goodkin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.model.ResponseDto;
import com.goodkin.model.site.Policy;
import com.goodkin.model.site.Site;
import com.goodkin.service.SiteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    /*
     * 정책 & 사이트 정보
     */
    @GetMapping("/admin/site/form")
    public ModelAndView getSite(ModelAndView mv) {
        return siteService.getSite(mv);
    }

    /*
     * 사이트 정보 업데이트
     */
    @PostMapping("/admin/site/update")
    @ResponseBody
    public ResponseDto<?> siteUpdate(Site site,
        @RequestPart(required = false) MultipartFile logofile) {
        return siteService.siteUpdate(site, logofile);
    }

    /*
     * 정책 업데이트
     */
    @PostMapping("/admin/policy/update")
    @ResponseBody
    public ResponseDto<?> policyUpdate(@RequestBody Policy policy) {
        return siteService.policyUpdate(policy);
    }
}
