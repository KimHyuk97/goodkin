package com.goodkin.repository;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.Admin;

@Mapper
public interface AdminRepository {

    Admin getAdmin(Long adminNo);
    
}
