package com.idealista.domain;

import java.util.List;

public interface AdsCollection {

    List<Ad> getAllAdsWithScore();

    List<Ad> getAllAds();

    void updateAllAds(List<Ad> updatedAds);
}
