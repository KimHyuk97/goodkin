package com.goodkin.model.admin;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Admin {
    private Long adminNo;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private AdminRole role;
    private Timestamp createDate;
    private Timestamp updateDate;
}
