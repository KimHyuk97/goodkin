package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodkin.model.page.Page;

@Mapper
public interface PageRepository {

    void insert(Page page);

    int update(Page page);

    List<Page> findAllByTypeAndCategory(@Param("category")String category, @Param("type")String type);

    Page getPage(Long pageId);
    
}
