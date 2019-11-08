package com.idealista.application.service;

import com.idealista.infrastructure.persistence.AdVO;

import java.util.List;

public interface AdService {
    List<AdVO> getOrderedRelevantAds();
    List<String> getAdPictureUrls(int id);
}