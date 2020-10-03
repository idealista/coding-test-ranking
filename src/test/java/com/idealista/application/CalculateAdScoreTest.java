package com.idealista.application;

import com.idealista.application.stubs.ExtractScoreValuesStub;
import com.idealista.application.stubs.InMemoryCollectionStub;
import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import com.idealista.domain.ExtractScoreValues;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculateAdScoreTest {

    private final AdsCollection adsCollection = new InMemoryCollectionStub();
    private final ExtractScoreValues extractScoreValues = new ExtractScoreValuesStub();
    private final Clock clock = Clock.systemDefaultZone();
    private final CalculateAdsScore calculateAdScore = new CalculateAdsScore(adsCollection, clock, extractScoreValues);

    @Test
    void should_Calculate_The_Score_For_The_Stored_Ads() {
        //when
        calculateAdScore.execute();

        //then
        final List<Ad> allAdsWithCalculatedScore = adsCollection.getAllAdsWithScore();
        assertFalse(allAdsWithCalculatedScore.isEmpty());
        allAdsWithCalculatedScore.forEach(ad ->
            assertNotNull(ad.getScore())
        );
    }
}
