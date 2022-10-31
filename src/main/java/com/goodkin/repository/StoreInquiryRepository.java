package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.store.StoreInquiry;

@Mapper
public interface StoreInquiryRepository {
    // 가맹점 리스트
    public List<StoreInquiry> getStoreInquirys();

    // 가맹점 단일 조회
    public StoreInquiry getStoreInquiry(Long storeInquiryNo);
    
    // 가맹점 등록
    public int save(StoreInquiry storeInquiry);

    // 가맹점 업데이트
    public int update(Long storeInquiryNo);

    // 가맹점 삭제
    public int delete(Long storeInquiryNo);
}
