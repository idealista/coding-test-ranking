package com.idealista.application;

import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import com.idealista.domain.services.ScoreCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class CalculateAdsScore {

    private final ScoreCalculator scoreCalculator;
    private final AdsCollection adsCollection;

    public CalculateAdsScore(AdsCollection adsCollection) {
        this.scoreCalculator = new ScoreCalculator();
        this.adsCollection = adsCollection;
    }

    public void execute() {
        final List<Ad> updatedAds = adsCollection.getAllAds().stream()
                .map(scoreCalculator::execute)
                .collect(Collectors.toList());
        adsCollection.updateAllAds(updatedAds);
    }
}
