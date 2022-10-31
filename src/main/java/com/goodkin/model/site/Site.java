package com.goodkin.model.site;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Site {
    private String logo;
    private String ceo;
    private String address;
    private String businessNumber;
    private String phone;
    private String copyright;
}
