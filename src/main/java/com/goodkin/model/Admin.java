package com.goodkin.model;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Admin {
    private Long adminNo;
    private String id;
    private String password;
    private String name;
    private String role;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;


    public static Admin toDto(Admin admin) {
        return Admin.builder()
                .adminNo(admin.getAdminNo())
                .id(admin.getId())
                .password(admin.getPassword())
                .name(admin.getName())
                .role(admin.getRole())
                .build();
    }
}
