package com.idealista.infrastructure.api.ad.score.service;

import com.idealista.infrastructure.api.ad.score.configuration.AdCompleteScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.ChaletAdCompleteAdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.FlatAdCompleteAdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.GarageAdCompleteAdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.idealista.infrastructure.api.ad.search.domain.AdPictureTestData.emptyAdPicture;
import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AdCompleteTest {

    private AdCompleteScoreConfiguration adCompleteScoreConfiguration;

    private AdScorer chaletAdCompleteAdScorer;
    private AdScorer flatAdCompleteAdScorer;
    private AdScorer garageAdCompleteAdScorer;

    @BeforeEach
    public void setup(){
        this.adCompleteScoreConfiguration = new AdCompleteScoreConfiguration(40);
        this.chaletAdCompleteAdScorer = new ChaletAdCompleteAdScorer(adCompleteScoreConfiguration);
        this.flatAdCompleteAdScorer = new FlatAdCompleteAdScorer(adCompleteScoreConfiguration);
        this.garageAdCompleteAdScorer = new GarageAdCompleteAdScorer(adCompleteScoreConfiguration);
    }

    @Test
    public void givenIncompleteFlat_whenGetScore_expectNoPoints(){
        //given
        Ad incompleteAd = flat()
            .description(LoremIpsum.getInstance().getWords(1))
            .adPictures(Arrays.asList(emptyAdPicture().build()))
            .houseSize(Optional.empty())
            .build();
        Integer expectedScore = 0;

        //when
        Integer actualScore = flatAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenCompleteFlat_whenGetScore_expectCompleteAdScore(){
        //given
        Ad incompleteAd = flat()
            .description(LoremIpsum.getInstance().getWords(1))
            .adPictures(Arrays.asList(emptyAdPicture().build()))
            .houseSize(Optional.of(100))
            .build();
        Integer expectedScore = adCompleteScoreConfiguration.getScore();

        //when
        Integer actualScore = flatAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenIncompleteChalet_whenGetScore_expectNoPoints(){
        //given
        Ad incompleteAd = chalet()
            .description(LoremIpsum.getInstance().getWords(1))
            .adPictures(Arrays.asList(emptyAdPicture().build()))
            .gardenSize(Optional.empty())
            .build();
        Integer expectedScore = 0;

        //when
        Integer actualScore = chaletAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenCompleteChalet_whenGetScore_expectCompleteAdScore(){
        //given
        Ad incompleteAd = chalet()
            .description(LoremIpsum.getInstance().getWords(1))
            .adPictures(Arrays.asList(emptyAdPicture().build()))
            .gardenSize(Optional.of(100))
            .houseSize(Optional.of(100))
            .build();
        Integer expectedScore = adCompleteScoreConfiguration.getScore();

        //when
        Integer actualScore = chaletAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenIncompleteGarage_whenGetScore_expectNoPoints(){
        //given
        Ad incompleteAd = garage()
            .adPictures(Collections.emptyList())
            .build();
        Integer expectedScore = 0;

        //when
        Integer actualScore = garageAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenCompleteGarage_whenGetScore_expectCompleteAdScore(){
        //given
        Ad incompleteAd = garage()
            .adPictures(Arrays.asList(emptyAdPicture().build()))
            .build();
        Integer expectedScore = adCompleteScoreConfiguration.getScore();

        //when
        Integer actualScore = garageAdCompleteAdScorer.getScore(incompleteAd);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}
