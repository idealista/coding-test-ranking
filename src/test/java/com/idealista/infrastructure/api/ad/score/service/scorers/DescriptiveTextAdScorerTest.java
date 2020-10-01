package com.idealista.infrastructure.api.ad.score.service;

import com.idealista.infrastructure.api.ad.score.configuration.AdDescriptionScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.DescriptiveTextAdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.emptyAd;
import static org.assertj.core.api.Assertions.assertThat;

public class DescriptiveTextAdScorerTest {

    private AdDescriptionScoreConfiguration adDescriptionScoreConfiguration;

    private AdScorer descriptiveTextAdScorer;

    @BeforeEach
    public void setup(){
        this.adDescriptionScoreConfiguration = new AdDescriptionScoreConfiguration();
        this.descriptiveTextAdScorer = new DescriptiveTextAdScorer(
            adDescriptionScoreConfiguration
        );
    }

    @Test
    public void givenNoDescription_whenGetScore_thenExpectNoPoints(){
        //given
        Ad adWithNoDescription = emptyAd().build();
        Integer expectedScore = 0;

        //when
        Integer actualScore = descriptiveTextAdScorer.getScore(adWithNoDescription);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenNoDescription_whenGetScore_thenExpectPoints(){
        //given
        Ad adWithDescription = emptyAd().description("Test Description").build();
        Integer descriptiveTextScore = 5;
        adDescriptionScoreConfiguration.setScore(descriptiveTextScore);

        //when
        Integer actualScore = descriptiveTextAdScorer.getScore(adWithDescription);

        //then
        assertThat(actualScore).isEqualTo(descriptiveTextScore);
    }
}
