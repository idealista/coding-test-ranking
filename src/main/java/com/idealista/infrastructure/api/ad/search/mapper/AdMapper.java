package com.idealista.infrastructure.api.ad.search.mapper;

import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import com.idealista.infrastructure.api.ad.search.domain.AdTypology;
import com.idealista.infrastructure.api.ad.search.dto.AdResponse;
import com.idealista.infrastructure.api.ad.search.dto.AdsResponse;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.api.ad.search.domain.AdPicture;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdMapper {

    public static AdVO adToAdVO(Ad ad){
        return AdVO.builder()
            .id(ad.getId())
            .typology(ad.getTypology().name())
            .description(ad.getDescription().orElse(null))
            .gardenSize(ad.getGardenSize().orElse(null))
            .houseSize(ad.getHouseSize().orElse(null))
            .adQuality(ad.getAdQuality().name())
            .irrelevantSince(ad.getIrrelevantSince().orElse(null))
            .pictures(ad.getAdPictures().stream().map(AdPicture::getId).collect(Collectors.toList()))
            .score(ad.getScore())
            .build();
    }

    public static Ad adVOToAd(AdVO adVo){
        return Ad.builder()
            .description(Optional.ofNullable(adVo.getDescription()))
            .gardenSize(Optional.ofNullable(adVo.getGardenSize()))
            .houseSize(Optional.ofNullable(adVo.getHouseSize()))
            .irrelevantSince(Optional.ofNullable(adVo.getIrrelevantSince()))
            .adPictures(null)
            .score(adVo.getScore())
            .id(adVo.getId())
            .adQuality(AdQuality.valueOf(adVo.getAdQuality()))
            .typology(AdTypology.valueOf(adVo.getTypology()))
            .build();
    }

    public static List<Ad> adsVOtoAds(List<AdVO> adsVO) {
        return adsVO.stream()
                .map(AdMapper::adVOToAd)
                .collect(Collectors.toList());
    }

    public static AdResponse adToAdResponse(Ad ad){
        return AdResponse.builder()
            .id(ad.getId())
            .description(ad.getDescription().orElse(null))
            .gardenSize(ad.getGardenSize().orElse(null))
            .houseSize(ad.getHouseSize().orElse(null))
            .irrelevantSince(ad.getIrrelevantSince().orElse(null))
            .adPictures(ad.getAdPictures())
            .score(ad.getScore())
            .typology(ad.getTypology())
            .adQuality(ad.getAdQuality())
            .build();
    }

    public static AdsResponse adsToAdsResponse(List<Ad> ads){
        return AdsResponse.builder()
            .ads(ads.stream().map(AdMapper::adToAdResponse).collect(Collectors.toList()))
            .build();
    }
}
