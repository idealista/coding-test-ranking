package com.idealista.infrastructure.services.ads.listing;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.collections.ListUtils.EMPTY_LIST;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetPublicAdsServiceImplTest {

    @InjectMocks
    private GetPublicAdsServiceImpl service;

    @Mock
    private AdsRepository adsVORepository;

    @Mock
    private ConversionService conversionService;

    @Test
    public void shouldReturnEmptyListWhenRetrieveNoAdVO() {
        //given
        when(adsVORepository.findAll()).thenReturn(EMPTY_LIST);
        //when
        List<PublicAd> out = service.get();
        //then
        assertTrue(out.isEmpty());
        verify(adsVORepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnPublicAdWhenRetrieveAdVO() {
        //given
        AdVO adVOChalet65 =  MockObjectFactory.getAdVOChalet65();
        AdVO adVOGarage5 = MockObjectFactory.getAdVOGarage5();
        AdVO adVOFlat85 =  MockObjectFactory.getAdVOFlat85();
        PublicAd publicAdChalet65 = MockObjectFactory.getQualityAdChalet65();
        PublicAd publicAdFlat85 = MockObjectFactory.getQualityAdFlat85();

        when(adsVORepository.findAll()).thenReturn(Arrays.asList(adVOChalet65, adVOGarage5, adVOFlat85));
        when(conversionService.convert(adVOChalet65, PublicAd.class)).thenReturn(publicAdChalet65);
        when(conversionService.convert(adVOFlat85, PublicAd.class)).thenReturn(publicAdFlat85);

        //when
        List<PublicAd> out = service.get();
        //then
        verify(adsVORepository, times(1)).findAll();
        verify(conversionService, times(1)).convert(adVOChalet65, PublicAd.class);
        verify(conversionService, times(1)).convert(adVOFlat85, PublicAd.class);
        verifyNoMoreInteractions(conversionService);
        assertThat(out, contains(publicAdFlat85,publicAdChalet65));
    }

}