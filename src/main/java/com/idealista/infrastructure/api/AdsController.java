package com.idealista.infrastructure.api;

import com.idealista.application.service.publicAd.PublicAdResponse;
import com.idealista.application.service.publicAd.PublicAdsService;
import com.idealista.application.service.qualityAd.QualityAdResponse;
import com.idealista.application.service.qualityAd.QualityAdsService;
import com.idealista.application.service.score.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdsController {

    private final PublicAdsService publicAdsService;
    private final QualityAdsService qualityAdsService;
    private final ScoreService scoreService;

    public AdsController(
            PublicAdsService publicAdsService,
            QualityAdsService qualityAdsService,
            ScoreService scoreService) {
        this.publicAdsService = publicAdsService;
        this.qualityAdsService = qualityAdsService;
        this.scoreService = scoreService;
    }

    @GetMapping("/ads")
    public ResponseEntity<List<PublicAdResponse>> publicListing() {
        final List<PublicAdResponse> publicAdResponses = publicAdsService.getAds();
        return ResponseEntity.ok(publicAdResponses);
    }

    @GetMapping("/ads/quality")
    public ResponseEntity<List<QualityAdResponse>> qualityListing() {
        final List<QualityAdResponse> qualityAdResponses = qualityAdsService.getAds();
        return ResponseEntity.ok(qualityAdResponses);
    }

    @PostMapping("/ads/score")
    public ResponseEntity<Void> calculateScore() {
        scoreService.calculateAdsScore();
        return ResponseEntity.noContent().build();
    }
}
