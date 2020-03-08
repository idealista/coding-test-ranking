package com.idealista.infrastructure.services.ads.listing;

import com.idealista.infrastructure.controllers.PublicAd;

import java.util.List;

@FunctionalInterface
public interface GetPublicAdsService {

    List<PublicAd> get();
}
