package com.idealista.application.service.publicAd;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublicAdsServiceTest {

    @Mock
    private AdRepository repository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private PublicAdsServiceImpl publicAdsServiceImpl;

    @Test
    public void shouldNotReturnAds() {
        when(repository.findPublicAds()).thenReturn(Collections.emptyList());

        assertThat(publicAdsServiceImpl.getAds()).isEmpty();

        verify(repository).findPublicAds();
        verify(conversionService, never()).convert(any(), any());
        verifyNoMoreInteractions(repository, conversionService);
    }

    @Test
    public void shouldReturnAdsConverted() {
        final Integer randomSize = BaseCreator.randomNumber();
        final List<Ad> ads = BaseCreator.randomList(randomSize, () -> AdCreator.create(true));

        when(repository.findPublicAds()).thenReturn(ads);
        when(conversionService.convert(any(), any())).thenReturn(PublicAdResponseCreator.create());

        final List<PublicAdResponse> publicAdResponses = publicAdsServiceImpl.getAds();
        assertThat(publicAdResponses).isNotEmpty();
        assertThat(publicAdResponses).hasSize(ads.size());

        verify(repository).findPublicAds();
        verify(conversionService, times(randomSize)).convert(any(), any());
        verifyNoMoreInteractions(repository, conversionService);
    }

    @Test
    public void shouldReturnAdsConvertedAndSorted() {
        final Ad ad15 = AdCreator.create();
        ad15.updateScore(15, 40);

        final Ad ad50 = AdCreator.create();
        ad50.updateScore(50, 40);

        final Ad ad75 = AdCreator.create();
        ad75.updateScore(75, 40);

        final Ad ad100 = AdCreator.create();
        ad100.updateScore(100, 40);

        final PublicAdResponse publicAdResponse15 = convertAdToPublicAdResponse(ad15);
        final PublicAdResponse publicAdResponse50 = convertAdToPublicAdResponse(ad50);
        final PublicAdResponse publicAdResponse75 = convertAdToPublicAdResponse(ad75);
        final PublicAdResponse publicAdResponse100 = convertAdToPublicAdResponse(ad100);

        final List<Ad> ads = Arrays.asList(ad15, ad100, ad75, ad50);

        when(repository.findPublicAds()).thenReturn(ads);
        when(conversionService.convert(ad15, PublicAdResponse.class)).thenReturn(publicAdResponse15);
        when(conversionService.convert(ad50, PublicAdResponse.class)).thenReturn(publicAdResponse50);
        when(conversionService.convert(ad75, PublicAdResponse.class)).thenReturn(publicAdResponse75);
        when(conversionService.convert(ad100, PublicAdResponse.class)).thenReturn(publicAdResponse100);

        final List<PublicAdResponse> publicAdResponses = publicAdsServiceImpl.getAds();
        assertThat(publicAdResponses).containsExactly(publicAdResponse100, publicAdResponse75, publicAdResponse50, publicAdResponse15);
    }

    private PublicAdResponse convertAdToPublicAdResponse(Ad ad) {
        final PublicAdResponse publicAdResponse = new PublicAdResponse();
        publicAdResponse.setId(ad.getId());
        publicAdResponse.setTypology(ad.getTypology().toString());
        publicAdResponse.setDescription(ad.getDescription());
        publicAdResponse.setPictureUrls(null);
        publicAdResponse.setHouseSize(ad.getHouseSize());
        publicAdResponse.setGardenSize(ad.getGardenSize());

        return publicAdResponse;
    }
}
