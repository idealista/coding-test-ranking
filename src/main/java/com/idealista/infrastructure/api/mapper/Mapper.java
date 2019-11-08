package com.idealista.infrastructure.api.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;

import java.util.List;

public class Mapper {
    public static PublicAd mapPublicAd(AdVO ad, List<String> imageUrls) {
        PublicAd publicAd = new PublicAd();

        publicAd.setId(ad.getId());
        publicAd.setTypology(ad.getTypology());
        publicAd.setDescription(ad.getDescription());
        publicAd.setHouseSize(ad.getHouseSize());
        publicAd.setGardenSize(ad.getGardenSize());

        return publicAd;
    }

    public static QualityAd mapQualityAd(AdVO ad, List<String> imageUrls) {
        QualityAd qualityAd = new QualityAd();

        qualityAd.setId(ad.getId());
        qualityAd.setTypology(ad.getTypology());
        qualityAd.setDescription(ad.getDescription());
        qualityAd.setHouseSize(ad.getHouseSize());
        qualityAd.setGardenSize(ad.getGardenSize());

        return qualityAd;
    }
}
