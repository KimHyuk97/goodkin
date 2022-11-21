package com.goodkin.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.page.Page;
import com.goodkin.repository.PageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final ResponseService responseService;
    private final Ftp ftp;

    /*
     * get 페이지
     */
    public ResponseDto<?> getPage(String category) {

        Map<String, List<Page>> data = new HashMap<>();

        List<Page> pc = pageRepository.findAllByTypeAndCategory(category, "pc");
        List<Page> mob = pageRepository.findAllByTypeAndCategory(category, "mob");

        data.put("pc", pc);
        data.put("mob", mob);
        
        return responseService.responseBuilder("", data);
    }

    /*
     * 단일 세션 페이지 업데이트
     */
    public ResponseDto<?> update(Long pageId, List<MultipartFile> files) {

        Page page = pageRepository.getPage(pageId);

        // 파일 업로드
        if (files != null && !files.isEmpty()) {
            String path = "/www/page/"+page.getCategory()+"/"+page.getType()+"/";

            if (page.getImg() != null) {
                String[] file = page.getImg().split("/");
                filedelete(Arrays.asList(file[file.length-1]), path);
            } 
            fileUpload(files, page, path);
        }

        // 업데이트
        pageRepository.update(page);

        return responseService.responseBuilder("수정되었습니다.", null);
    }


    private void fileUpload(List<MultipartFile> files, Page page, String path) {
        try {
            List<String> fileNames = ftp.uploadFile(files, path);
            
            if (!fileNames.isEmpty()) {
                page.setImg("https://joeunfc2022.cdn1.cafe24.com/page/"+page.getCategory()+"/"+page.getType()+"/"+fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filedelete(List<String> files, String path) {
        try {
            ftp.deleteFile(files, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
