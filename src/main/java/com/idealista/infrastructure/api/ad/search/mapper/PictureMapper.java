package com.idealista.infrastructure.api.ad.search.mapper;

import com.idealista.infrastructure.persistence.Picture;
import com.idealista.infrastructure.persistence.PictureQuality;
import com.idealista.infrastructure.persistence.PictureVO;

import java.util.List;
import java.util.stream.Collectors;

public class PictureMapper {

    public static Picture pictureVOToPicture(PictureVO pictureVO){
        return Picture.builder()
            .id(pictureVO.getId())
            .quality(PictureQuality.valueOf(pictureVO.getQuality()))
            .url(pictureVO.getUrl())
            .build();
    }

    public static List<Picture> picturesVOToPictures(List<PictureVO> picturesVO){
        return picturesVO.stream()
            .map(PictureMapper::pictureVOToPicture)
            .collect(Collectors.toList());
    }

}
