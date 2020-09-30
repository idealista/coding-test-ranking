package com.idealista.domain;

import com.idealista.domain.services.Ad;
import com.idealista.domain.services.AdIdentifer;
import com.idealista.domain.services.Picture;
import com.idealista.domain.services.ScoreCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoreCalculatorTest {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    @DisplayName("Given an ad with a HD picture When the score is calculated Then The score should be 20")
    public void should_Calculate_The_Score_For_An_Ad_With_HD_Picture() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "FLAT", null, Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(20), calculatedScoreAd.getScore());
    }
}
