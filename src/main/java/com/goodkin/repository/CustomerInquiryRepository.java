package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.Pagination;
import com.goodkin.model.customerInquiry.CustomerInquiry;
import com.goodkin.model.customerInquiry.CustomerInquiryFile;
import com.goodkin.model.customerInquiry.InquiryType;

@Mapper
public interface CustomerInquiryRepository {

    // 고객문의
    public List<CustomerInquiry> getCustomerInquiryList();

    public CustomerInquiry getCustomerInquiry(Long customerInquiryNo);

    public CustomerInquiry findByType(InquiryType type);
    
    public CustomerInquiry findByCustomerName(String customerName);

    public CustomerInquiry findByStoreNo(Long storeNo);

    public int save(CustomerInquiry customerInquiry);

    public int update(CustomerInquiry customerInquiry);

    public int delete(Long customerInquiryNo);

    // 첨부파일
    public List<CustomerInquiryFile> getFiles(@Param("fileNos") List<Long> fileNos);

    public CustomerInquiryFile getFile(Long customerInquiryFileNo);

    public List<CustomerInquiryFile> getCustomerInquiryFiles(Long customerInquiryNo);

    public int insertFiles(@Param("files") List<CustomerInquiryFile> files);

    public int deleteFiles(@Param("fileNos") List<Long> fileNos);

    // 관리자
    public int listCount(@Param("kind")String kind, @Param("keyword")String keyword, @Param("type") String type);

    public List<CustomerInquiry> list(@Param("kind")String kind, @Param("keyword")String keyword, @Param("type") String type, @Param("paging") Pagination paging);
    
}
