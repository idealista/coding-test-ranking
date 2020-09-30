package com.idealista.domain;

import com.idealista.domain.services.Ad;
import com.idealista.domain.services.ScoreCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idealista.domain.mothers.AdMother.*;
import static com.idealista.domain.services.ScoreCalculator.PICTURE_HD_SCORE;
import static com.idealista.domain.services.ScoreCalculator.PICTURE_SD_SCORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoreCalculatorTest {

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    @Test
    @DisplayName("Given an ad with a HD picture When the score is calculated Then The score should be 20")
    public void should_Calculate_The_Score_For_An_Ad_With_HD_Picture() {
        //given
        final Ad ad = createAdWithASingleHDPicture();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(PICTURE_HD_SCORE), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with three HD pictures When the score is calculated Then The score should be 60")
    public void should_Calculate_The_Score_For_An_Ad_With_Three_HD_Picture() {
        //given
        final Ad ad = createAdWithAThreeHDPictures();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(60), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a SD pictures When the score is calculated Then The score should be 10")
    public void should_Calculate_The_Score_For_An_Ad_With_A_SD_Picture() {
        //given
        final Ad ad = createAdWithASingleSDPicture();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(PICTURE_SD_SCORE), calculatedScoreAd.getScore());
    }
}
