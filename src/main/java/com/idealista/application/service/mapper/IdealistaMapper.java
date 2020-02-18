package com.idealista.application.service.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

import java.util.Date;
import java.util.stream.Collectors;

public class IdealistaMapper {
    public static QualityAd mapAdVOToQualityAd(AdVO adVO){
        return QualityAd.builder()
                .id(adVO.getId())
                .description(adVO.getDescription())
                .houseSize(adVO.getHouseSize())
                .gardenSize(adVO.getGardenSize())
                .typology(adVO.getTypology())
                .score(adVO.getScore())
                .irrelevantSince(adVO.getScore() < 40 ? (adVO.getIrrelevantSince() == null ? adVO.getIrrelevantSince() : new Date()) : null)
                .pictureUrls(adVO.getPictures().stream().map(IdealistaMapper::mapPictureIdToUrl).collect(Collectors.toList()))
                .build();
    }

    public static PublicAd mapQualityAdToPublicAd(QualityAd qualityAd){
        return PublicAd.builder()
                .id(qualityAd.getId())
                .description(qualityAd.getDescription())
                .houseSize(qualityAd.getHouseSize())
                .gardenSize(qualityAd.getGardenSize())
                .typology(qualityAd.getTypology())
                .pictureUrls(qualityAd.getPictureUrls())
                .build();
    }

    public static String mapPictureIdToUrl(Integer pictureId){
        String URL_PICTURES = "http://www.idealista.com/pictures/";
        return String.format("%s%d", URL_PICTURES, pictureId);
    }

    public static int mapValueFromPhotoQuality(PictureVO pictureVO){
        return pictureVO.getQuality().equals("HD") ? 20 : 10;
    }
}
