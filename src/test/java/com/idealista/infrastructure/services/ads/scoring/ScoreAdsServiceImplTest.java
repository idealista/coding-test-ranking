package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.exceptions.ScoringIncompleteException;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

import static org.apache.commons.collections.ListUtils.EMPTY_LIST;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScoreAdsServiceImplTest {

    @InjectMocks
    private ScoreAdsServiceImpl service;

    @Mock
    private AdsRepository adsVORepository;

    @Test
    public void shouldNotScoreWhenRetrieveNoAdVO() throws ScoringIncompleteException {
        //given
        when(adsVORepository.findAll()).thenReturn(EMPTY_LIST);
        //when
        service.score();
        //then
        verify(adsVORepository, times(1)).findAll();
        verify(adsVORepository, times(0)).saveOrUpdate(any());
    }

    @Test
    public void shouldCalculateScoreListWhenRetrieveNoAdVO() throws ScoringIncompleteException {
        //given
        AdVO adVOChalet65 =  MockObjectFactory.getAdVOChalet65();
        adVOChalet65.setScore(10);
        adVOChalet65.setIrrelevantSince(new Date());

        AdVO adVOGarage5 = MockObjectFactory.getAdVOGarage5();
        adVOGarage5.setScore(80);
        adVOChalet65.setIrrelevantSince(null);

        AdVO adVOFlat85 =  MockObjectFactory.getAdVOFlat85();
        adVOGarage5.setScore(null);
        adVOChalet65.setIrrelevantSince(null);

        when(adsVORepository.findAll()).thenReturn(Arrays.asList(adVOChalet65, adVOGarage5, adVOFlat85));

        //when
        service.score();
        //then
        verify(adsVORepository, times(1)).findAll();
        verify(adsVORepository, times(3)).saveOrUpdate(any());
    }

}