package com.idealista.infrastructure.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idealista.domain.service.PublicAdsService;

@RequestMapping("/ads")
@RestController
public class AdsController {
	
	@Autowired
	private PublicAdsService publicAdService;

    //TODO añade url del endpoint
    public ResponseEntity<List<QualityAd>> qualityListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PublicAd>> publicListing() {
        return ResponseEntity.ok(publicAdService.getAds());
    }

    //TODO añade url del endpoint
    public ResponseEntity<Void> calculateScore() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }
}
