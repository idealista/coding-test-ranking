package com.idealista.infrastructure.persistence;

import com.idealista.domain.Ad;
import com.idealista.domain.AdsCollection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryAdsCollection implements AdsCollection {

    private final InMemoryPersistence inMemoryPersistence;

    private final Function<AdVO, Ad> adVOToDomainMapper;

    private final Function<Ad, AdVO> domainAdToVOMapper;

    public InMemoryAdsCollection(InMemoryPersistence inMemoryPersistence, Function<AdVO, Ad> adVOToDomainMapper, Function<Ad, AdVO> domainAdToVOMapper) {
        this.inMemoryPersistence = inMemoryPersistence;
        this.adVOToDomainMapper = adVOToDomainMapper;
        this.domainAdToVOMapper = domainAdToVOMapper;
    }

    @Override
    public List<Ad> getAllAdsWithScore() {
        return inMemoryPersistence.findAllAds().stream()
                .filter(updatedAdVO -> null != updatedAdVO.getScore())
                .map(adVOToDomainMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ad> getAllAds() {
        return inMemoryPersistence.findAllAds().stream().map(adVOToDomainMapper).collect(Collectors.toList());
    }

    @Override
    public void updateAllAds(Ad updatedAd) {
        inMemoryPersistence.updateAd(domainAdToVOMapper.apply(updatedAd));
    }


}
