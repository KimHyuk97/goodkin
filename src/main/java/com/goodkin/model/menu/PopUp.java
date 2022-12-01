package com.goodkin.model.menu;

import lombok.Data;

/*
 *  팝업 관리 DTO
 */

@Data
public class PopUp {
    private Long popupNo;       // 고유키
    private String fileUrl;     // 파일 URL
    private String fileName;    // 파일 이름
    private String targetUrl;   // 팝업 클릭 시 이동 URL
    private String createDate;  // 등록 일자
}
