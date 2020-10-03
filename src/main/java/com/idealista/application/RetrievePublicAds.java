package com.idealista.application;

import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RetrievePublicAds {

    private final AdsCollection adsCollection;

    public RetrievePublicAds(AdsCollection adsCollection) {
        this.adsCollection = adsCollection;
    }

    public List<Ad> execute() {
        return adsCollection.getAllAdsWithScore().stream().filter(Ad::isNotIrrelevant).sorted(scoreComparator).collect(Collectors.toList());
    }

    Comparator<Ad> scoreComparator = (ad1, ad2) -> ad2.getScore().compareTo(ad1.getScore());

}
