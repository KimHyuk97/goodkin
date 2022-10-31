package com.goodkin.model.admin;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
