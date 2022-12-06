package com.goodkin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.goodkin.model.ResponseDto;
import com.goodkin.service.PopUpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/*
 *  팝업 관리 Controller
 */

@Controller
@RequestMapping("/admin/popup")
public class PopUpController {
    
    @Autowired(required = false)
    PopUpService popUpService;

    /*
     * 팝업 리스트
     */
    @GetMapping("/list")
    public ModelAndView popupList(ModelAndView mv) {

        return popUpService.popupList(mv);
    }

    /*
     *  팝업 등록 insert
     */
    @PostMapping(value="/insert")
    @ResponseBody
    public ResponseDto<?> popupInsert(@RequestPart List<MultipartFile> multipartFiles){
        return popUpService.popupInsert(multipartFiles);
    }
    
    /*
     *  팝업 등록 update
     */
    @PostMapping(value="/update")
    @ResponseBody
    public ResponseDto<?> popupUpdate(@RequestPart List<MultipartFile> multipartFiles){
        return popUpService.popupUpdate(multipartFiles);
    }

    /*
     *  팝업 URL 등록
     */
    @PostMapping(value="/updatePopUpUrl")
    @ResponseBody
    public ResponseDto<?> updatePopUpUrl(@RequestBody Map<String, String> map) {
        System.err.println(map.get("url"));
        return popUpService.updatePopUpUrl(map.get("url"));
    }
    
    
}
