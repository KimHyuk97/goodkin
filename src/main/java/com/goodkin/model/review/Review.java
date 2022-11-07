package com.goodkin.model.review;

import lombok.Data;

@Data
public class Review {
    private Long reviewNo;
    private String file;
    private String fileUrl;
    private String createDate;

    public Review(String file, String fileUrl) {
        this.file = file;
        this.fileUrl = fileUrl;
    }
    
}
