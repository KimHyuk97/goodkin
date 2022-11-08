package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.Pagination;
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
    public int update(StoreInquiry storeInquiry);

    // 가맹점 삭제
    public int delete(Long storeInquiryNo);

    // 관리자 리스트페이지 총갯수
    public int listCount(@Param("kind")String kind, @Param("keyword")String keyword,  @Param("address") String address, @Param("subAddress") String subAddress);

    // 관리자 리스트페이지
    public List<StoreInquiry> list(@Param("kind")String kind, @Param("keyword")String keyword,  @Param("address") String address, @Param("subAddress") String subAddress, @Param("paging")Pagination paging);
}
