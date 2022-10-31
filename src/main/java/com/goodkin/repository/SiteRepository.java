package com.goodkin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.site.Site;

@Mapper
public interface SiteRepository {

    // 사이트 정보 조회
    public Site getSite();
    
    // 사이트 정보 업데이트
    public int update(Long SiteNo);
    
}
