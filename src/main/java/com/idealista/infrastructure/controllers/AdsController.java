package com.idealista.infrastructure.controllers;

import java.util.List;

import com.idealista.infrastructure.services.GetPublicAdsService;
import com.idealista.infrastructure.services.GetQualityAdsService;
import com.idealista.infrastructure.services.ScoreAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
public class AdsController {

    private final GetPublicAdsService getPublicAdsService;
    private final GetQualityAdsService getQualityAdsService;
    private final ScoreAdsService scoreAdsService;

    @Autowired
    public AdsController(GetPublicAdsService getPublicAdsService, GetQualityAdsService getQualityAdsService,
                         ScoreAdsService scoreAdsService) {
        this.getPublicAdsService = getPublicAdsService;
        this.getQualityAdsService = getQualityAdsService;
        this.scoreAdsService = scoreAdsService;
    }

    @GetMapping("/admin/ads" )
    public ResponseEntity<List<QualityAd>> qualityListing() {
        List<QualityAd> out = getQualityAdsService.get();
        if(isEmpty(out))
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(out);
    }

    @GetMapping("/ads")
    public ResponseEntity<List<PublicAd>> publicListing() {
        List<PublicAd> out = getPublicAdsService.get();
        if(isEmpty(out))
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(out);
    }

    @PostMapping("/ads")
    public ResponseEntity<Void> calculateScore() {
        scoreAdsService.score();
        return ResponseEntity.noContent().build();
    }
}
