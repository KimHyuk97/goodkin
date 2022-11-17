package com.goodkin.model.page;

import lombok.Data;

@Data
public class Page {
    private Long pageId;
    private String img;
    private String category;
    private String section;
    private String type;
    private int maxWidth;
    private int maxHeight;
}
