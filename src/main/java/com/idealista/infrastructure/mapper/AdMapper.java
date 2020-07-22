package com.idealista.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;

public class AdMapper {
  public List<PublicAd> fromAdVoListToPublicAdList(List<AdVO> ads) {
    return ads.stream()
        .map(adVO ->
            PublicAd.builder()
                .description(adVO.getDescription())
                .houseSize(adVO.getHouseSize())
                .id(adVO.getId())
                .pictureUrls(getUrls(adVO.getPictures()))
                .gardenSize(adVO.getGardenSize())
                .typology(adVO.getTypology())
                .build())
        .collect(Collectors.toList());
  }

  public List<QualityAd> fromAdVoListToQualityAdList(List<AdVO> ads) {
    return ads.stream()
        .map(adVO ->
            QualityAd.builder()
                .description(adVO.getDescription())
                .houseSize(adVO.getHouseSize())
                .id(adVO.getId())
                .pictureUrls(getUrls(adVO.getPictures()))
                .gardenSize(adVO.getGardenSize())
                .typology(adVO.getTypology())
                .irrelevantSince(adVO.getIrrelevantSince())
                .score(adVO.getScore())
                .build()).collect(Collectors.toList());
  }

  private List<String> getUrls(List<Integer> pictureIds) {
    return pictureIds.stream()
        .map(picId -> "http://www.idealista.com/pictures/".concat(picId.toString()))
        .collect(Collectors.toList());
  }
}
