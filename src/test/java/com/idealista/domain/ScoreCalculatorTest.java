package com.idealista.domain;

import com.idealista.domain.services.Ad;
import com.idealista.domain.services.AdIdentifer;
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

    @Test
    @DisplayName("Given an ad with a description When the score is calculated Then the score should be 5")
    void should_Calculate_The_Score_For_An_Ad_With_Description() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), null, "This is a description", null, null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(5), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length between 20 and 49 and typology 'FLAT' When the score is calculated Then the score should be 15")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Between_20_And_49_And_Typology_Flat() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "FLAT", "This is a description", null, null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(15), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length greater than 50 and typology 'FLAT' When the score is calculated Then the score should be 35")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Greater_Than_50_And_Typology_Flat() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "FLAT", "This is a description too much long to check the score calculator", null, null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(35), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length greater than 50 and typology 'CHALET' When the score is calculated Then the score should be 35")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Greater_Than_50_And_Typology_Chalet() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "CHALET", "This is a description too much long to check the score calculator", null, null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(25), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description that contains the word 'luminoso' When the score is calculated Then the score should be 10")
    void should_Calculate_The_Score_For_An_Ad_With_Description_That_Contains_A_Special_Word() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "FLAT", "This is so luminoso", null, null, null, null, null);

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(10), calculatedScoreAd.getScore());
    }


}
