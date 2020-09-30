package com.idealista.infrastructure.api.ad.search.adapter.impl;

import com.idealista.infrastructure.api.ad.search.adapter.AdUpdateRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.persistence.InMemoryPersistenceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.idealista.infrastructure.api.ad.search.mapper.AdMapper.adToAdVO;

@Component
@RequiredArgsConstructor
public class AdUpdateRepositoryAdapterImpl implements AdUpdateRepositoryAdapter {

    private final InMemoryPersistenceImpl inMemoryPersistenceImpl;

    @Override
    public void updateAll(List<Ad> ads) {
        ads.stream().forEach(this::update);
    }

    @Override
    public void update(Ad ad){
        inMemoryPersistenceImpl.update(adToAdVO(ad));
    }
}
