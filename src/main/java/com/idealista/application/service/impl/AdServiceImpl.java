package com.idealista.application.service.impl;

import com.idealista.application.service.AdService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private InMemoryPersistence inMemoryPersistence;

    public AdServiceImpl(InMemoryPersistence inMemoryPersistence) { this.inMemoryPersistence = inMemoryPersistence; }

    @Override
    public List<AdVO> getOrderedRelevantAds() {
        return inMemoryPersistence.findAllAdsIrrelevantSinceIsNullOrderByScoreDesc();
    }

    @Override
    public List<String> getAdPictureUrls(int adId) {
        return inMemoryPersistence.findAdPicturesUrlById(adId);
    }
}
