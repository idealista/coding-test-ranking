package com.idealista.infrastructure.controllers;

import java.util.List;

import com.idealista.infrastructure.exceptions.ScoringIncompleteException;
import com.idealista.infrastructure.services.ads.listing.GetPublicAdsService;
import com.idealista.infrastructure.services.ads.listing.GetQualityAdsService;
import com.idealista.infrastructure.services.ads.scoring.ScoreAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    //@PreAuthorize("hasRole('ADMIN')")
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

    @PostMapping("/ads/score")
    public ResponseEntity<Void> calculateScore() {
        try {
            scoreAdsService.score();
            return ResponseEntity.noContent().build();
        } catch (ScoringIncompleteException exc) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Incomplete scoring ", exc);
        }
    }
}
