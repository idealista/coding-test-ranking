package com.idealista.infrastructure.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.idealista.application.service.AdRatingService;
import com.idealista.application.service.AdService;
import com.idealista.infrastructure.api.mapper.Mapper;
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
    private AdService adService;

    public AdsController(AdRatingService adRatingService, AdService adService) {
        this.adRatingService = adRatingService;
        this.adService = adService;
    }
    @Autowired
    InMemoryPersistence inMemoryPersistence;

    //TODO añade url del endpoint
    public ResponseEntity<List<QualityAd>> qualityListing() {

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ads/public")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<AdVO> ads = adService.getOrderedRelevantAds();
        System.out.println(ads);

        List publicAds = ads.stream()
                .map(ad -> Mapper.mapPublicAd(
                        ad,
                        adService.getAdPictureUrls(ad.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(publicAds, HttpStatus.OK);
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
