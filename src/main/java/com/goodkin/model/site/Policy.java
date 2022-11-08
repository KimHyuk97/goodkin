package com.goodkin.model.site;

import lombok.Data;

@Data
public class Policy {
    private Long policyNo;
    private PolicyType policyType;
    private String content;
    private String createDate;
    private String updateDate;
}
