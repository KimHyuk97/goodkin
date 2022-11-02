package com.goodkin.naver;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GeocodeController {
    
    private final GeocodeService geocodeService;

    @PostMapping("/map/geocode/{query}")
    public GeocodeDto getGeocode(@PathVariable String query) {
        return geocodeService.getGeocode(query);
    }

}
