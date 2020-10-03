package com.idealista.application;

import com.idealista.domain.AdsCollection;
import com.idealista.domain.services.ScoreCalculator;

import java.time.Clock;
import java.util.stream.Collectors;

public class CalculateAdsScore {

    private final ScoreCalculator scoreCalculator;
    private final AdsCollection adsCollection;
    private final Clock clock;

    public CalculateAdsScore(AdsCollection adsCollection, Clock clock) {
        this.clock = clock;
        this.scoreCalculator = new ScoreCalculator(clock);
        this.adsCollection = adsCollection;
    }

    public void execute() {
        adsCollection.getAllAds().stream()
            .map(scoreCalculator::execute)
            .collect(Collectors.toList())
            .forEach(adsCollection::updateAllAds);
    }
}
