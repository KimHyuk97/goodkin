package com.goodkin.model.store;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Store {
    private Long storeNo;
    private String name;
    private String address;
    private String phone;
    private BigDecimal x;
    private BigDecimal y;
    private String service;
    private String createDate;
    private String updateDate;
}
