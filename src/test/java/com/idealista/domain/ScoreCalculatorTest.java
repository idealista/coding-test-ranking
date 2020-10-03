package com.idealista.domain;

import com.idealista.domain.mothers.AdMother;
import com.idealista.domain.services.ScoreCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

import static com.idealista.domain.conditions.PicturesScoreRule.PICTURE_HD_SCORE;
import static com.idealista.domain.conditions.PicturesScoreRule.PICTURE_SD_SCORE;
import static com.idealista.domain.mothers.AdMother.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreCalculatorTest {

    private final Clock clock = mock(Clock.class);
    private final ScoreCalculator scoreCalculator = new ScoreCalculator(clock);

    @Test
    @DisplayName("Given an ad without properties to obtain score and without pictures When the score is calculated Then The score should be 0")
    void should_Calculate_The_Score_For_An_Ad_Without_Properties_To_Obtain_Score() {
        //given
        final Ad ad = createAdWithoutPictures();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(0), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad without pictures When the score is calculated Then The score should be decreased in 10")
    void should_Calculate_The_Score_For_An_Ad_Without_Pictures() {
        //given
        final Ad ad = createAdOfFlatTypologyWithShortDescriptionWithoutPictures();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(5), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a HD picture When the score is calculated Then The score should be increased in 20")
    public void should_Calculate_The_Score_For_An_Ad_With_HD_Picture() {
        //given
        final Ad ad = createAdWithASingleHDPicture();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(PICTURE_HD_SCORE), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with three HD pictures When the score is calculated Then The score should be increased in 60")
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
    @DisplayName("Given an ad with a SD pictures When the score is calculated Then The score should be increased in 10")
    public void should_Calculate_The_Score_For_An_Ad_With_A_SD_Picture() {
        //given
        final Ad ad = createAdWithASingleSDPicture();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(PICTURE_SD_SCORE), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description When the score is calculated Then the score should be increased in 5")
    void should_Calculate_The_Score_For_An_Ad_With_Description() {
        //given
        final Ad ad = createAdWithDescription();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(25), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length between 20 and 49 and typology 'FLAT' When the score is calculated Then the score should be increased in 10")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Between_20_And_49_And_Typology_Flat() {
        //given
        final Ad ad = createAdOfFlatTypologyWithShortDescription();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(35), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length greater than 50 and typology 'FLAT' When the score is calculated Then the score should be increased in 30")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Greater_Than_50_And_Typology_Flat() {
        //given
        final Ad ad = createAdOfFlatTypologyWithLongDescription();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(55), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description with length greater than 50 and typology 'CHALET' When the score is calculated Then the score should be increased in 20")
    void should_Calculate_The_Score_For_An_Ad_With_Description_Greater_Than_50_And_Typology_Chalet() {
        //given
        final Ad ad = AdMother.createAdOfChaletTypologyWithLongDescription();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(45), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description that contains a special word When the score is calculated Then the score should be increased in 5")
    void should_Calculate_The_Score_For_An_Ad_With_Description_That_Contains_A_Special_Word() {
        //given
        final Ad ad = createAdWithSpecialWordInDescription();
        mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(30), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad with a description that contains two special When the score is calculated Then the score should be increased in 5 for each word.")
    void should_Calculate_The_Score_For_An_Ad_With_Description_That_Contains_Two_Special_Words() {
        //given
        final Ad ad = createAdWithMultipleSpecialWordsInDescription();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(45), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an complete ad for a flat When the score is calculated Then the score should be increased in 40.")
    void should_Calculate_The_Score_For_A_Complete_Ad_With_Flat_Typology() {
        //given
        final Ad ad = createCompleteAdWithFlatTypology();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(75), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an complete ad for a chalet When the score is calculated Then the score should be increased in 40.")
    void should_Calculate_The_Score_For_A_Complete_Ad_With_Chalet_Typology() {
        //given
        final Ad ad = createCompleteAdWithChaletTypology();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(65), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an complete ad for a garage When the score is calculated Then the score should be increased in 40.")
    void should_Calculate_The_Score_For_A_Complete_Ad_With_Garage_Typology() {
        //given
        final Ad ad = createCompleteAdWithGarageTypology();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertEquals(Integer.valueOf(60), calculatedScoreAd.getScore());
    }

    @Test
    @DisplayName("Given an ad When his score is less to 40 Then the date should be stored")
    void should_Add_The_Date_Of_The_Execution_If_The_Score_Is_Irrelevant() {
        //given
        final Ad ad = new Ad(new AdIdentifer(1), "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", emptyList(), 70, 25, null, null);
        final Instant instant = mockInstantNowExecution();

        //when
        final Ad calculatedScoreAd = scoreCalculator.execute(ad);

        //then
        assertNotNull(calculatedScoreAd);
        assertNotNull(calculatedScoreAd.getIrrelevantSince());
        assertEquals(Date.from(instant), calculatedScoreAd.getIrrelevantSince());
    }

    private Instant mockInstantNowExecution() {
        final Instant instant = Instant.now();
        when(clock.instant()).thenReturn(instant);
        return instant;
    }
}
