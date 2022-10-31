package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.customerInquiry.CustomerInquiry;
import com.goodkin.model.customerInquiry.CustomerInquiryFile;
import com.goodkin.model.customerInquiry.InquiryType;

@Mapper
public interface CustomerInquiryRepository {

    // 고객문의
    public List<CustomerInquiry> getCustomInquiryList();

    public CustomerInquiry getCustomInquiry(Long customerInquiryNo);

    public CustomerInquiry findByType(InquiryType type);
    
    public CustomerInquiry findByCustomerName(String customerName);

    public CustomerInquiry findByStoreNo(Long storeNo);

    public int save(CustomerInquiry customerInquiry);

    public int update(Long customerInquiryNo);

    public int delete(Long customerInquiryNo);

    // 첨부파일
    public List<CustomerInquiryFile> getCustomerInquiryFiles(Long customerInquiryNo);

    public int insertFiles(List<CustomerInquiryFile> files);

    public int deleteFiles(Long[] fileNos);
    
}
