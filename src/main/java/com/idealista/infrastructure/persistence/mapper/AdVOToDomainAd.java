package com.idealista.infrastructure.persistence.mapper;

import com.idealista.domain.Ad;
import com.idealista.domain.AdIdentifer;
import com.idealista.domain.Picture;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AdVOToDomainAd implements Function<AdVO, Ad> {

    private final InMemoryPersistence inMemoryPersistence;

    public AdVOToDomainAd(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public Ad apply(AdVO adVO) {
        final List<Picture> pictures = adVO.getPictures().stream()
                .map(inMemoryPersistence::findPictureById)
                .map(pictureVOToDomainPicture())
                .collect(Collectors.toList());
        return new Ad(new AdIdentifer(adVO.getId()), adVO.getTypology(), adVO.getDescription(), pictures, adVO.getHouseSize(), adVO.getGardenSize(), adVO.getScore(), adVO.getIrrelevantSince());
    }


    private Function<PictureVO, Picture> pictureVOToDomainPicture() {
        return pictureVO -> new Picture(pictureVO.getId(), pictureVO.getUrl(), pictureVO.getQuality());
    }
}
