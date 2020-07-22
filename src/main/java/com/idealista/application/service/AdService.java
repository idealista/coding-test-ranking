package com.idealista.application.service;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;

public interface AdService {

  List<AdVO> getPublicAds();

  List<AdVO> getQualityAds();

  List<AdVO> calculateAdsWithScore();
}
