package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.application.service.AdRatingService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdsController {
    private AdRatingService adRatingService;

    public AdsController(AdRatingService adRatingService) { this.adRatingService = adRatingService; }
    @Autowired
    InMemoryPersistence inMemoryPersistence;

    //TODO añade url del endpoint
    public ResponseEntity<List<QualityAd>> qualityListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    //TODO añade url del endpoint
    public ResponseEntity<List<PublicAd>> publicListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ads/rate")
    public ResponseEntity<Void> calculateScore() {
        adRatingService.rateAds();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<List> getAds() {
        return new ResponseEntity<>(inMemoryPersistence.findAllAds(), HttpStatus.OK);
    }
}
