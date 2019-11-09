package com.idealista.infrastructure.api.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;

import java.util.List;

public class Mapper {
    public static PublicAd mapPublicAd(AdVO ad, List<String> imageUrls) {
        return new PublicAd(
                ad.getId(),
                ad.getTypology(),
                ad.getDescription(),
                imageUrls,
                ad.getHouseSize(),
                ad.getGardenSize()
        );
    }

    public static QualityAd mapQualityAd(AdVO ad, List<String> imageUrls) {
        return new QualityAd(
                ad.getId(),
                ad.getTypology(),
                ad.getDescription(),
                imageUrls,
                ad.getHouseSize(),
                ad.getGardenSize(),
                ad.getScore(),
                ad.getIrrelevantSince()
        );
    }
}
