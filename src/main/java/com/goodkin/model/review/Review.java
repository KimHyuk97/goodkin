package com.goodkin.model.review;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Review {
    private Long reviewNo;
    private String file;
    private String fileUrl;
    private String content;
    private String createDate;
}
