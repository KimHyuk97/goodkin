package com.goodkin.model.menu;

import lombok.Data;

@Data
public class MainMenu {
    private Long mainMenuNo;
    private Long menuNo;
    private Integer sort;
    
    private String name;
    private String mainFileUrl;   
    private MenuCategroy category;
}
