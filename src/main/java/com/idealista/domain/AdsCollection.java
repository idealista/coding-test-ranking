package com.idealista.domain;

import java.util.List;

public interface AdsCollection {

    List<Ad> getAllAdsWithScore();

    void saveAll(List<Ad> storedAds);
}
