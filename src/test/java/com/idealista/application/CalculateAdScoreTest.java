package com.idealista.application;

import com.idealista.application.stubs.InMemoryCollectionStub;
import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculateAdScoreTest {

    private final AdsCollection adsCollection = new InMemoryCollectionStub();
    private final CalculateAdsScore calculateAdScore = new CalculateAdsScore(adsCollection);

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
