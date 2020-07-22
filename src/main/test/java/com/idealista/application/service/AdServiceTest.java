//package com.idealista.application.service;
//
//import com.idealista.application.repository.AdRepository;
//import com.idealista.application.service.impl.AdServiceImpl;
//import com.idealista.infrastructure.persistence.AdVO;
//import com.idealista.infrastructure.persistence.PictureVO;
//import com.sun.tools.javac.util.List;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//public class AdServiceTest {
//
//  private final AdRepository adRepository = Mockito.mock(AdRepository.class);
//
//  private final AdService adService = new AdServiceImpl(adRepository);
//  private AdVO adVO;
//  private PictureVO pictureVO;
//
//  @Before
//  public void setUp() throws Exception {
//    adVO = AdVO.builder()
//        .description("Description")
//        .gardenSize(23)
//        .houseSize(65)
//        .id(1)
//        .typology("Flat")
//        .build();
//    pictureVO = PictureVO.builder()
//        .id(1)
//        .url("http://www.idealista.com/pictures/1")
//        .quality("HD")
//        .build();
//  }
//
//  @Test
//  public void getPublicAds() {
//  }
//
//  @Test
//  public void getQualityAds() {
//  }
//
//  @Test
//  public void calculateAdsWithScore() {
//    when(adRepository.findPictureVOById(any())).thenReturn(pictureVO);
//    when(adRepository.findAdVO(any())).thenReturn(List.of(adVO));
//    adService.calculateAdsWithScore();
//
//  }
//}