package com.goodkin.model.customerInquiry;

import java.util.List;

import com.goodkin.model.store.Store;

import java.util.ArrayList;

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
    private String createDate;
    private String updateDate;
    
    private List<CustomerInquiryFile> files = new ArrayList<>();
    
    private Store store;
    private Long storeNo;
    private String name;
    private String address;
    private String detailAddress;
    private String phone;
}
