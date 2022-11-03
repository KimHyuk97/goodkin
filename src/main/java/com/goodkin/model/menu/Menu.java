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
    private String createDate;
    private String updateDate;
}
