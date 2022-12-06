package com.goodkin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.menu.PopUp;

@Mapper
public interface PopUpRepository {

    /**
     * 팝업 등록
     * @param popup 
     */
    void popupInsert(PopUp popup);

    /**
     * 팝업 리스트 조회 
     * @param popupNo
     * @return
     */
    PopUp getPopUp(Long popupNo);

    /**
     * 팝업 업데이트
     * @param popup
     */
    void popUpdate(PopUp popup);

    /**
     * url 조회
     * @return
     */
    PopUp getPopUpUrl();

    /**
     * url update
     * @param url
     */
    void updatePopUpUrl(String url);
    
}
