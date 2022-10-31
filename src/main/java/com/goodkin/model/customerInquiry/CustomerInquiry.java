package com.goodkin.model.customerInquiry;

import java.util.List;

import com.goodkin.model.store.Store;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomerInquiry {
    private Long customerInquiryNo;
    private InquiryType type;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String title;
    private String content;
    private Long storeNo;
    private String createDate;

    private List<CustomerInquiryFile> files = new ArrayList<>();
    private Store store;

    @Builder
    public CustomerInquiry(Long customerInquiryNo, InquiryType type, String customerName, String customerPhone,
            String customerEmail, String title, String content, Long storeNo, String createDate,
            List<CustomerInquiryFile> files, Store store) {
        this.customerInquiryNo = customerInquiryNo;
        this.type = type;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.title = title;
        this.content = content;
        this.storeNo = storeNo;
        this.createDate = createDate;
        this.files = files;
        this.store = store;
    }
        
}
