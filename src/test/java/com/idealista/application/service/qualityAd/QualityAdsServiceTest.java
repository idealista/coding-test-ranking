package com.idealista.application.service.qualityAd;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdCreator;
import com.idealista.domain.ad.AdRepository;
import com.idealista.util.BaseCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QualityAdsServiceTest {

    @Mock
    private AdRepository repository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private QualityAdsServiceImpl qualityAdsService;

    @Test
    public void shouldNotReturnAds() {
        when(repository.findIrrelevantAds()).thenReturn(Collections.emptyList());

        assertThat(qualityAdsService.getAds()).isEmpty();

        verify(repository).findIrrelevantAds();
        verify(conversionService, never()).convert(any(), any());
        verifyNoMoreInteractions(repository, conversionService);
    }

    @Test
    public void shouldReturnAdsConverted() {
        final Integer randomSize = BaseCreator.randomNumber();
        final List<Ad> ads = BaseCreator.randomList(randomSize, () -> AdCreator.create(true));

        when(repository.findIrrelevantAds()).thenReturn(ads);
        when(conversionService.convert(any(), any())).thenReturn(QualityAdResponseCreator.create());

        final List<QualityAdResponse> qualityAdResponses = qualityAdsService.getAds();
        assertThat(qualityAdResponses).isNotEmpty();
        assertThat(qualityAdResponses).hasSize(ads.size());

        verify(repository).findIrrelevantAds();
        verify(conversionService, times(randomSize)).convert(any(), any());
        verifyNoMoreInteractions(repository, conversionService);
    }
}
