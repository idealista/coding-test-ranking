package com.idealista.application;

import com.idealista.application.stubs.InMemoryCollectionStub;
import com.idealista.domain.Ad;
import com.idealista.domain.AdIdentifer;
import com.idealista.domain.AdsCollection;
import com.idealista.domain.Picture;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculateAdScoreTest {

    private final AdsCollection adsCollection = new InMemoryCollectionStub();
    private final CalculateAdsScore calculateAdScore = new CalculateAdsScore(adsCollection);

    @Test
    void should_Calculate_The_Score_For_The_Stored_Ads() {
        //given
        final List<Ad> storedAds = Arrays.asList(
                new Ad(new AdIdentifer(1), "GARAGE", null, singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), 70, 25, null, null),
                new Ad(new AdIdentifer(2), "CHALET", "This is a description too much long to check the score calculator", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null),
                new Ad(new AdIdentifer(3), "FLAT", "Contains luminoso and reformado", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null)
        );
        adsCollection.saveAll(storedAds);

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
