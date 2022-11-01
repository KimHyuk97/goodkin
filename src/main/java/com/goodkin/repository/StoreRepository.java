package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.Pagination;
import com.goodkin.model.store.Store;

@Mapper
public interface StoreRepository {
    
    // 가맹점 리스트
    public List<Store> getStores();

    // 가맹점 단일 조회
    public Store getStore(Long storeNo);
    
    // 가맹점 등록
    public int save(Store store);

    // 가맹점 업데이트
    public int update(Store store);

    // 가맹점 삭제
    public int delete(Long storeNo);

    // 관리자 리스트 카운트 개수
    public int listCount(@Param("kind")String kind, @Param("keyword")String keyword,  @Param("address") String address, @Param("subAddress") String subAddress);

    // 관리자 리스트
    public List<Store> list(@Param("kind")String kind, @Param("keyword")String keyword,  @Param("address") String address, @Param("subAddress") String subAddress, @Param("paging") Pagination paging);

}
