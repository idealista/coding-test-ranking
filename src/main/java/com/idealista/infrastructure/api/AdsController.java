package com.idealista.infrastructure.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idealista.domain.service.PublicAdService;
import com.idealista.domain.service.QualityAdService;
import com.idealista.domain.service.ScoreAdService;

@RequestMapping("/ads")
@RestController
public class AdsController {
	
	@Autowired
	private PublicAdService publicAdService;
	
	@Autowired
	private QualityAdService qualityAdService;
	
	@Autowired
	private ScoreAdService scoreAdService;

    @GetMapping("/quality")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        return ResponseEntity.ok(qualityAdService.getAds());
    }

    @GetMapping
    public ResponseEntity<List<PublicAd>> publicListing() {
        return ResponseEntity.ok(publicAdService.getAds());
    }

    @PostMapping("/score")
    public ResponseEntity<Void> calculateScore() {
        scoreAdService.calculateScore();
        return ResponseEntity.noContent().build();
    }
}
