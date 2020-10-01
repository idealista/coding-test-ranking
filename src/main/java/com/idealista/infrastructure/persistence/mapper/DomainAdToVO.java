package com.idealista.infrastructure.persistence.mapper;

import com.idealista.domain.Ad;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DomainAdToVO implements Function<Ad, AdVO> {

    private final InMemoryPersistence inMemoryPersistence;

    public DomainAdToVO(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public AdVO apply(Ad ad) {
        final List<Integer> pictureIds = ad.getPictures().stream()
                .map(picture -> inMemoryPersistence.findPictureByUrl(picture.getUrl())).map(PictureVO::getId).collect(Collectors.toList());
        return new AdVO(ad.getId().getValue(), ad.getTypology().name(), ad.getDescription(), pictureIds, ad.getHouseSize(), ad.getGardenSize(), ad.getScore(), ad.getIrrelevantSince());
    }
}
