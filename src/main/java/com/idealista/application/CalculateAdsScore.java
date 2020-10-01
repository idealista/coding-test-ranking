package com.idealista.application;

import com.idealista.domain.AdsCollection;
import com.idealista.domain.services.ScoreCalculator;

import java.util.stream.Collectors;

public class CalculateAdsScore {

    private final ScoreCalculator scoreCalculator;
    private final AdsCollection adsCollection;

    public CalculateAdsScore(AdsCollection adsCollection) {
        this.scoreCalculator = new ScoreCalculator();
        this.adsCollection = adsCollection;
    }

    public void execute() {
        adsCollection.getAllAds().stream()
            .map(scoreCalculator::execute)
            .collect(Collectors.toList())
            .forEach(adsCollection::updateAllAds);
    }
}
