package com.idealista.application;

import com.idealista.domain.AdsCollection;
import com.idealista.domain.ExtractScoreValues;
import com.idealista.domain.services.ScoreCalculator;

import java.time.Clock;
import java.util.stream.Collectors;

public class CalculateAdsScore {

    private final ScoreCalculator scoreCalculator;
    private final AdsCollection adsCollection;
    private final Clock clock;
    private final ExtractScoreValues extractScoreValues;

    public CalculateAdsScore(AdsCollection adsCollection, Clock clock, ExtractScoreValues extractScoreValues) {
        this.clock = clock;
        this.extractScoreValues = extractScoreValues;
        this.adsCollection = adsCollection;
        this.scoreCalculator = new ScoreCalculator(extractScoreValues, clock);
    }

    public void execute() {
        adsCollection.getAllAds().stream()
            .map(scoreCalculator::execute)
            .collect(Collectors.toList())
            .forEach(adsCollection::updateAllAds);
    }
}
