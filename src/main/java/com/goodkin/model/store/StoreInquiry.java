package com.goodkin.model.store;

import lombok.Data;

@Data
public class StoreInquiry {
    private Long storeInquiryNo;
    private String customerName;
    private String customerPhone;
    private String desiredArea;
    private Integer budget;
    private String content;
    private Boolean currentStoreOperateStatus;
    private Boolean policyStatus;
    private String createDate;
}
