package com.idealista.application.service.rating.impl;

import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageRatingRuleService implements RatingRuleService {
    private static final int NO_PICTURE = -10;
    private static final int HD_PICTURE = 20;
    private static final int SD_PICTURE = 10;

    private InMemoryPersistence inMemoryPersistence;

    public ImageRatingRuleService(InMemoryPersistence inMemoryPersistence) { this.inMemoryPersistence = inMemoryPersistence; }

    @Override
    public int calculate(AdVO ad) {
        List<PictureVO> pictures = getPictures(ad.getPictures());

        if (pictures.isEmpty()) {
            return NO_PICTURE;
        }

        int score = pictures
                .stream()
                .mapToInt(this::getPictureScore)
                .sum();

        return score;
    }

    private List<PictureVO> getPictures(List<Integer> pictureIds) {
        List<PictureVO> pictures = new ArrayList<>();

         pictureIds
                 .stream()
                 .forEach(id -> {
                     Optional<PictureVO> pictureOptional = inMemoryPersistence.findPictureById(id);

                     pictureOptional.ifPresent(pictureVO -> pictures.add(pictureVO));
                 });

         return pictures;
    }

    private int getPictureScore(PictureVO pictureVO) {
        if (pictureVO.isHD()) {
            return HD_PICTURE;
        }

        return SD_PICTURE;
    }
}
