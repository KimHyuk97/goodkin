package com.goodkin.model.menu;


import lombok.Data;

@Data
public class Menu {
    private Long menuNo;
    private MenuCategroy category;
    private String name;
    private String description;
    private String file;
    private String fileUrl;
    private Boolean newStatus;
    private Boolean expStatus;
    
    // 메인 메뉴
    private Long mainMenuNo;
    private Integer sort;

    private String createDate;
    private String updateDate;

}
