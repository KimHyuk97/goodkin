package com.goodkin.model.store;

import com.goodkin.naver.GeocodeDto;

import lombok.Data;

@Data
public class Store {
    private Long storeNo;
    private String name;
    private String address;
    private String detailAddress;
    private String zonecode;
    private String phone;
    private String x;
    private String y;
    private String service;
    private Boolean exposureStatus;
    private String createDate;
    private String updateDate;

    public void addressToGeocodeAddress(GeocodeDto geocodeDto) {
        this.address = geocodeDto.getAddresses().get(0).getJibunAddress();
        this.x = geocodeDto.getAddresses().get(0).getX();
        this.y = geocodeDto.getAddresses().get(0).getY();
    }
}
