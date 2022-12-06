package com.goodkin.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodkin.ftp.Ftp;
import com.goodkin.model.ResponseDto;
import com.goodkin.model.menu.PopUp;
import com.goodkin.repository.PopUpRepository;

/*
 *  팝업 관리 Service
 */

@Service
public class PopUpService {
    
    @Autowired
    PopUpRepository popUpRepository;
    
    @Autowired
    ResponseService responseService;

    @Autowired
    Ftp ftp;


    /*
     *  팝업 등록
     */
    public ResponseDto<?> popupInsert(List<MultipartFile> multipartFiles) {
        PopUp popup = new PopUp();

        try {
            List<String> fileNames = ftp.uploadFile(multipartFiles, "/www/popup/");
            if (!fileNames.isEmpty()) {
                popup.setFileName(fileNames.get(0));
                popup.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/popup/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        popUpRepository.popupInsert(popup);
        return responseService.responseBuilder("팝업이 등록 되었습니다.", null);
    }

    /*
     *  팝업 등록 update
     */
    public ResponseDto<?> popupUpdate(List<MultipartFile> multipartFiles) {
        PopUp popup = popUpRepository.getPopUp(1L);

        // 사진 삭제
        try {
            if(popup != null) {
                ftp.deleteFile(Arrays.asList(popup.getFileName()), 
                    "/www/popup/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 사진등록
        try {
            List<String> fileNames = ftp.uploadFile(multipartFiles, "/www/popup/");
            if (!fileNames.isEmpty() && popup != null) {
                popup.setFileName(fileNames.get(0));
                popup.setFileUrl("https://joeunfc2022.cdn1.cafe24.com/popup/" + fileNames.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(popup != null) popup.setPopupNo(1L);
        popUpRepository.popUpdate(popup);
        return responseService.responseBuilder("등록되었습니다.", null);
    }

    /*
     * 팝업 리스트
     */
    public ModelAndView popupList(ModelAndView mv) {
        PopUp popup = popUpRepository.getPopUp(1L);

        mv.addObject("item", popup != null ? popup : new PopUp());
        mv.setViewName("admin/popup/list");
        return mv;
    }

    /*
     *  팝업 URL 등록
     */
    public ResponseDto<?> updatePopUpUrl(String url) {

        // url 조회
        PopUp select = popUpRepository.getPopUpUrl();
        if(select != null) popUpRepository.updatePopUpUrl(url);
        else return responseService.responseBuilder("팝업 사진을 먼저 등록해주세요.", null);

        return responseService.responseBuilder("등록되었습니다.", null);
    }


}
