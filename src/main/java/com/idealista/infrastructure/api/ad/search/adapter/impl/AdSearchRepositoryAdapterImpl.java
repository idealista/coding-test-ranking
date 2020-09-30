package com.idealista.infrastructure.api.ad.search.adapter.impl;

import com.idealista.infrastructure.api.ad.search.mapper.AdMapper;
import com.idealista.infrastructure.api.ad.search.adapter.AdSearchRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistenceImpl;
import com.idealista.infrastructure.api.ad.search.domain.AdPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

import static com.idealista.infrastructure.api.ad.search.mapper.AdMapper.adsVOtoAds;
import static com.idealista.infrastructure.api.ad.search.mapper.PictureMapper.picturesVOToPictures;

@Component
@RequiredArgsConstructor
public class AdSearchRepositoryAdapterImpl implements AdSearchRepositoryAdapter {

    private final InMemoryPersistenceImpl inMemoryPersistenceImpl;

    @Override
    public List<Ad> getAllAds() {
        List<AdVO> adsVo = inMemoryPersistenceImpl.getAllAds();
        List<Ad> ads = adsVOtoAds(adsVo);
        ads.forEach(ad -> ad.setAdPictures(getPicturesFromAd(adsVo.get(ad.getId()))));
        return ads;
    }

    @Override
    public Ad getAdById(Integer id) {
        AdVO adVo = inMemoryPersistenceImpl.getAdById(id).orElseThrow(NoSuchElementException::new);
        Ad ad = AdMapper.adVOToAd(adVo);
        ad.setAdPictures(getPicturesFromAd(adVo));
        return ad;
    }

    private List<AdPicture> getPicturesFromAd(AdVO adVo){
        return picturesVOToPictures(inMemoryPersistenceImpl.getPicturesById(adVo.getPictures()));
    }
}
