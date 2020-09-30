package com.idealista.infrastructure.api.ad.score.controller;

import com.idealista.infrastructure.api.ad.score.service.AdScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdScoreController {

    private final AdScoreService adScoreService;

    @PostMapping("/scores")
    public void generateAdScores(){
        adScoreService.calculateAdsScore();
    }

}
