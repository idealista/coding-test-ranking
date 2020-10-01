package com.idealista.application.stubs;

import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCollectionStub implements AdsCollection {

    private final List<Ad> adStorage = new ArrayList<>();

    @Override
    public List<Ad> getAllAdsWithScore() {
        return adStorage.stream().filter(ad -> ad.getScore() != null).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Ad> storedAds) {
        adStorage.addAll(storedAds);
    }
}
