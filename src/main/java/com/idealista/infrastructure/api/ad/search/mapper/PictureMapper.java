package com.idealista.infrastructure.api.ad.search.mapper;

import com.idealista.infrastructure.api.ad.search.domain.AdPicture;
import com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality;
import com.idealista.infrastructure.persistence.PictureVO;

import java.util.List;
import java.util.stream.Collectors;

public class PictureMapper {

    public static AdPicture pictureVOToPicture(PictureVO pictureVO){
        return AdPicture.builder()
            .id(pictureVO.getId())
            .quality(AdPictureQuality.valueOf(pictureVO.getQuality()))
            .url(pictureVO.getUrl())
            .build();
    }

    public static List<AdPicture> picturesVOToPictures(List<PictureVO> picturesVO){
        return picturesVO.stream()
            .map(PictureMapper::pictureVOToPicture)
            .collect(Collectors.toList());
    }

}
