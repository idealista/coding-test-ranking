package com.idealista.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.idealista.application.stubs.InMemoryCollectionStub;
import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import java.time.Clock;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculateAdScoreTest {

    private final AdsCollection adsCollection = new InMemoryCollectionStub();
    private final Clock clock = Clock.systemDefaultZone();
    private final CalculateAdsScore calculateAdScore = new CalculateAdsScore(adsCollection, clock);

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
