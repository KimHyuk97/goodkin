package com.goodkin.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.site.Policy;
import com.goodkin.model.site.PolicyType;
import com.goodkin.model.site.Site;
import com.goodkin.repository.PolicyRepository;
import com.goodkin.repository.SiteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final PolicyRepository policyRepository;
    private final SiteRepository siteRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

    /**
     * 사이트 정보 & 정책
     */
    public ModelAndView getSite(ModelAndView mv) {
        
        // 개인정보취급방침
        Policy privacyPolicy = policyRepository.getPolicy(PolicyType.PRIVACY);

        // 사이즈 정보
        Site site = siteRepository.getSite();

        // 각 페이지 사진 정보
        

        mv.addObject("privacyPolicy", privacyPolicy);
        mv.addObject("site", site);
        mv.setViewName("admin/site/form");
        return mv;
    }

    @Transactional
    public ResponseDto<?> siteUpdate(Site site, MultipartFile logofile) {
        
        // 파일 업로드
        if(logofile != null && !logofile.isEmpty()) {
            // 파일 삭제
            if(site != null && site.getLogo() != null) {
                String[] logos = site.getLogo().split("/");
                filedelete(Arrays.asList(logos[logos.length -1]));
            }

            fileUpload(logofile, site);
        }

        int update = siteRepository.update(site);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

    
    private void fileUpload(MultipartFile logofile, Site site) {
        String path = "/www/site/";
    
        try {
            List<String> fileNames = ftp.uploadFile(Arrays.asList(logofile), path);
            if(!fileNames.isEmpty()) {
                site.setLogo("https://joeunfc2022.cdn1.cafe24.com/site/"+fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filedelete(List<String> files) {
        String path = "/www/site/";

        try {
            ftp.deleteFile(files, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public ResponseDto<?> policyUpdate(Policy policy) {
        int update = policyRepository.update(policy);
        
        return responseService.responseBuilder(update > 0 ? "수정되었습니다." : "수정 실패하였습니다.", null);
    }

}
