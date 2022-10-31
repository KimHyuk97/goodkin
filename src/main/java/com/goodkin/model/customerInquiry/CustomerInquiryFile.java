package com.goodkin.model.customerInquiry;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerInquiryFile {
    private Long customerInquiryFileNo;
    private String file;
    private String fileUrl;
    private Long customerInquiryNo;
}
