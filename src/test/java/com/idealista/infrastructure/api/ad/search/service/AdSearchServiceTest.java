package com.idealista.infrastructure.api.ad.search.service;

import com.idealista.infrastructure.api.ad.search.adapter.AdSearchRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.service.impl.AdSearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.IRRELEVANT;
import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.RELEVANT;
import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.*;
import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AdSearchServiceTest {

    @Mock
    private AdSearchRepositoryAdapter adSearchRepositoryAdapter;

    private AdSearchService adSearchService;

    @BeforeEach
    public void setup(){
        adSearchService = new AdSearchServiceImpl(
          adSearchRepositoryAdapter
        );
    }

    @Test
    public void givenAds_whenGetAllAds_expectAllAds(){
        //given
        List<Ad> ads = Arrays.asList(
            irrelevantAd().build(),
            relevantAd().build(),
            notScoredAd().build()
        );
        given(adSearchRepositoryAdapter.getAllAds())
            .willReturn(ads);

        //when
        List<Ad> actualAds = adSearchService.getAllAds(Optional.empty());

        //then
        assertThat(actualAds).isEqualTo(ads);
    }

    @Test
    public void givenAds_whenGetIrrelevantAds_expectIrrelevantAds(){
        //given
        List<Ad> ads = Arrays.asList(
            irrelevantAd().build(),
            relevantAd().build(),
            notScoredAd().build()
        );
        List<Ad> expectedAds = Arrays.asList(
            irrelevantAd().build()
        );
        given(adSearchRepositoryAdapter.getAllAds())
            .willReturn(ads);

        //when
        List<Ad> actualAds = adSearchService.getAllAds(Optional.of(IRRELEVANT));

        //then
        assertThat(actualAds).isEqualTo(expectedAds);
    }

    @Test
    public void givenAds_whenGetRelevantAds_expectRelevantAdsSortedByScore(){
        //given
        List<Ad> relevantAds = Arrays.asList(
            relevantAd().score(40).build(),
            relevantAd().score(50).build()
        );
        List<Ad> reversedSortedRelevantAds = relevantAds.stream()
            .sorted(comparing(Ad::getScore).reversed())
            .collect(Collectors.toList());
        given(adSearchRepositoryAdapter.getAllAds())
            .willReturn(reversedSortedRelevantAds);

        //when
        List<Ad> allAds = adSearchService.getAllAds(Optional.of(RELEVANT));

        //then
        assertThat(allAds).isEqualTo(reversedSortedRelevantAds);
    }
}
