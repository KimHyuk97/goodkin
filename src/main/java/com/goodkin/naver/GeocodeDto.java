package com.goodkin.naver;

import java.util.List;

import lombok.Data;

@Data
public class GeocodeDto {
    private String status;
    private Meta meta;
    private List<Addresses> addresses;
    private String errorMessage;

    @Data
    public static class Meta {
        private Integer totalCount;
        private Integer page;
        private Integer count;
    }

    @Data
    public static class Addresses {
        private String roadAddress;
        private String jibunAddress;
        private String englishAddress;
        private AddressElement[] addressElements;
        private String x;
        private String y;
        private double distance;
    }

    @Data
    public static class AddressElement {
        private String[] types;
        private String longName;
        private String shortName;
        private String code;
    }
}