package com.goodkin.model.customerInquiry;

import lombok.Data;

@Data
public class CustomerInquiryFile {
    private Long customerInquiryFileNo;
    private String file;
    private String fileUrl;
    private Long customerInquiryNo;

    public CustomerInquiryFile(Long customerInquiryNo, String file, String fileUrl) {
        this.customerInquiryNo = customerInquiryNo;
        this.file = file;
        this.fileUrl = fileUrl;
    }    
}
