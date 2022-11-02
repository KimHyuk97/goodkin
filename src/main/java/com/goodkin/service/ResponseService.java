package com.goodkin.service;

import org.springframework.stereotype.Service;

import com.goodkin.model.ResponseDto;

@Service
public class ResponseService {

    public<T> ResponseDto<?> responseBuilder(String message, T data) {
        return ResponseDto.builder()
                        .message(message)
                        .data(data)
                        .build();
    }
    
}
