package com.idealista.infrastructure.api.inbound;

import com.idealista.application.CalculateAdsScore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateScoreController {

    private final CalculateAdsScore calculateAdsScore;

    public CalculateScoreController(CalculateAdsScore calculateAdsScore) {
        this.calculateAdsScore = calculateAdsScore;
    }

    @GetMapping("/api/1/ad/calculate-score")
    public ResponseEntity<Void> calculateScore() {
        calculateAdsScore.execute();
        return ResponseEntity.ok().build();
    }
}
