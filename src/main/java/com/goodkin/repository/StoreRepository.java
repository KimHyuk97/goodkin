package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
    public int update(Long storeNo);

    // 가맹점 삭제
    public int delete(Long storeNo);

}
