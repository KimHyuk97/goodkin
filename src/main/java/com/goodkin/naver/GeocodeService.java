package com.goodkin.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodeService {

    private final ObjectMapper objectMapper;

    @Value("${naver.client.id}")
    String clientId;

    @Value("${naver.client.secret}")
    String clientSecret;
    
    public GeocodeDto getGeocode(String query) {

        System.err.println("query : "+query);
        
        RestTemplate restTemplate = new RestTemplate();
        
        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.add("X-NCP-APIGW-API-KEY", clientSecret);

        //HttpHeader, HttpBody를 하나의 오브젝트로 담음
        HttpEntity<MultiValueMap<String, String>> kakakTokenRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+query,
            HttpMethod.GET,
            kakakTokenRequest,
            String.class
        );

        GeocodeDto geocodeDto = null;

        try {
            geocodeDto = objectMapper.readValue(response.getBody(), GeocodeDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return geocodeDto;
    }
}
