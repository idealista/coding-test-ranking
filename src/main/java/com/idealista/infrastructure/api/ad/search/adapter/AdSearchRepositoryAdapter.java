package com.idealista.infrastructure.api.ad.search.adapter;

import com.idealista.infrastructure.api.ad.search.domain.Ad;

import java.util.List;

public interface AdSearchRepositoryAdapter {

    List<Ad> getAllAds();

    Ad getAdById(Integer id);

}
