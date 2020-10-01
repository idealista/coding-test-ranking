package com.idealista.infrastructure.api.ad.score.service;

import com.idealista.infrastructure.api.ad.score.configuration.AdDescriptionScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.configuration.AdScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.impl.AdScoreServiceImpl;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.DescriptiveTextAdScorer;
import com.idealista.infrastructure.api.ad.search.adapter.AdSearchRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.adapter.AdUpdateRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AdScoreServiceTest {

    @Mock
    private AdSearchRepositoryAdapter adSearchRepositoryAdapter;
    @Mock
    private AdUpdateRepositoryAdapter adUpdateRepositoryAdapter;
    @Mock
    private Clock clock;

    private AdScoreConfiguration adScoreConfiguration;

    private List<AdScorer> adScorers;

    private AdScoreService adScoreService;

    private Integer descriptiveTextScore = 10;

    private Integer relevantMinScore = 5;

    @Captor
    private ArgumentCaptor<List<Ad>> adCaptor;

    @BeforeEach
    public void setup(){
        this.adScorers = Arrays.asList(
            new DescriptiveTextAdScorer(
                new AdDescriptionScoreConfiguration(descriptiveTextScore)
            )
        );
        this.adScoreConfiguration = new AdScoreConfiguration(10, 0, relevantMinScore);
        this.adScoreService = new AdScoreServiceImpl(
            adScorers,
            adScoreConfiguration,
            adSearchRepositoryAdapter,
            adUpdateRepositoryAdapter,
            clock
        );
    }

    @Test
    public void givenAdWithNoDescription_whenCalculateScore_expectAdToBeIrrelevant(){
        //given
        Ad adWithNoDescription = emptyAd().build();
        Instant irrelevantInstant = Instant.now();
        Ad irrelevantAd = irrelevantAd()
            .score(0)
            .irrelevantSince(Optional.of(Date.from(irrelevantInstant))).build();

        given(adSearchRepositoryAdapter.getAllAds())
            .willReturn(Arrays.asList(adWithNoDescription));
        given(clock.instant())
            .willReturn(irrelevantInstant);

        //when
        adScoreService.calculateAdsScore();

        Mockito.verify(adUpdateRepositoryAdapter).updateAll(adCaptor.capture());

        //then
        Ad scoredAd = Optional.ofNullable(adCaptor.getValue().get(0)).orElseThrow(NullPointerException::new);
        assertThat(scoredAd).isEqualTo(irrelevantAd);
    }

    @Test
    public void givenAdWithDescription_whenCalculateScore_expectAdToBeIrrelevant(){
        //given
        String description = "description";
        Ad adWithDescription = emptyAd().description(Optional.of(description)).build();
        Ad relevantAd = relevantAd()
            .description(Optional.of(description))
            .score(descriptiveTextScore)
            .build();

        given(adSearchRepositoryAdapter.getAllAds())
            .willReturn(Arrays.asList(adWithDescription));

        //when
        adScoreService.calculateAdsScore();

        Mockito.verify(adUpdateRepositoryAdapter).updateAll(adCaptor.capture());

        //then
        Ad scoredAd = Optional.ofNullable(adCaptor.getValue().get(0)).orElseThrow(NullPointerException::new);
        assertThat(scoredAd).isEqualTo(relevantAd);
    }
}
