package com.idealista.infrastructure.api.ad.search.adapter;

import com.idealista.infrastructure.api.ad.search.domain.Ad;

import java.util.List;

public interface AdUpdateRepositoryAdapter {

    void updateAll(List<Ad> ads);

    void update(Ad ad);
}
