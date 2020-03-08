package com.idealista.infrastructure.services;

import com.idealista.infrastructure.controllers.QualityAd;

import java.util.List;

@FunctionalInterface
public interface GetQualityAdsService {

    List<QualityAd> get();
}
