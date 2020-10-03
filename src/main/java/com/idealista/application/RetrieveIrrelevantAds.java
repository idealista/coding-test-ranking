package com.idealista.application;

import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import java.util.List;
import java.util.stream.Collectors;

public class RetrieveIrrelevantAds {

  private final AdsCollection adsCollection;

  public RetrieveIrrelevantAds(AdsCollection adsCollection) {
    this.adsCollection = adsCollection;
  }

  public List<Ad> execute() {
    return adsCollection.getAllAdsWithScore().stream().filter(Ad::isIrrelevant).collect(Collectors.toList());
  }

}
