package com.idealista.infrastructure.services;

import com.idealista.infrastructure.controllers.PublicAd;

import java.util.List;

@FunctionalInterface
public interface GetPublicAdsService {

    List<PublicAd> get();
}
