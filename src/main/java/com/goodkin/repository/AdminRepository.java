package com.goodkin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodkin.model.DelModel;
import com.goodkin.model.admin.Admin;
import com.goodkin.model.admin.AdminRole;

@Mapper
public interface AdminRepository {

    
    List<Admin> getAdminList();
    
    Admin getAdmin(Long adminNo);
    
    Admin findByAdminRole(AdminRole adminRole);
    
    Admin findByName(String name);
    
    Admin findById(String id);

    int save(Admin admin);

    int update(Admin admin);

    int delete(Long adminNo);

    void mutldel(DelModel delModel);
       
}
