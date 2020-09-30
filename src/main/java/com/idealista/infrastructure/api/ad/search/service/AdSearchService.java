package com.idealista.infrastructure.api.ad.search.service;

import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdQuality;

import java.util.List;
import java.util.Optional;

public interface AdSearchService {

    List<Ad> getAllAds(Optional<AdQuality> adQuality);

    Ad getAdById(Integer id);

}
