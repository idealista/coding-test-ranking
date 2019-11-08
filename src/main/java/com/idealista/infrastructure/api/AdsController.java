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

    @GetMapping("/ads/quality")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<AdVO> ads = adService.getAll();

        List qualityAds = ads.stream()
                .map(ad -> Mapper.mapQualityAd(
                        ad,
                        adService.getAdPictureUrls(ad.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(qualityAds, HttpStatus.OK);
    }

    @GetMapping("/ads/irrelevant")
    public ResponseEntity<List<QualityAd>> irrelevantListing() {
        List<AdVO> ads = adService.getIrrelevantAds();

        List qualityAds = ads.stream()
                .map(ad -> Mapper.mapQualityAd(
                        ad,
                        adService.getAdPictureUrls(ad.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(qualityAds, HttpStatus.OK);
    }

    @GetMapping("/ads/public")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<AdVO> ads = adService.getOrderedRelevantAds();

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
}
