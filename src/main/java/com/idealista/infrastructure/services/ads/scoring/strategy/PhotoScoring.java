package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;
import com.idealista.infrastructure.services.ads.common.AdVOConditions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.idealista.infrastructure.services.ads.common.AdVOConditions.*;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class PhotoScoring extends AbstractScoring { //implements Scoring

    private static Integer SCORE_NO_PHOTO = -10;

    private enum PhotoQualityScore {
        HD("HD", 20),
        SD("SD", 10);

        private String quality;
        private Integer score;

        public String getQuality() {
            return quality;
        }

        public Integer getScore() {
            return score;
        }

        PhotoQualityScore(String quality, Integer score) {
            this.quality = quality;
            this.score = score;
        }

        private static final Map<String, PhotoQualityScore> nameIndex = Arrays.stream(PhotoQualityScore.values())
                        .collect(Collectors.toMap(PhotoQualityScore::getQuality, Function.identity()));

        public static PhotoQualityScore lookupByName(String name) {
            return nameIndex.get(name);
        }
    }

    @Override
    public Integer calculateScoring(AdVO adVO) {
        Integer score = SCORE_NO_PHOTO;

        if(hasPhoto(adVO).getAsBoolean()) {
            score = getPicturesScoring(adVO.getPictures());
        }
        return score;
    }

    private Integer getPicturesScoring(List<PictureVO> pictures) {
        return pictures.stream()
                .map(this::getPictureScoring)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer getPictureScoring(PictureVO pictureVO) {
        return Optional.ofNullable(pictureVO)
                .map(PictureVO::getQuality)
                .map(this::getQualityScoring)
                .orElse(DEFAULT_SCORE);
    }

    @SuppressWarnings("ConstantConditions")
    private Integer getQualityScoring(String quality) {
        return Optional.ofNullable(quality)
                .map(PhotoQualityScore::lookupByName)
                .filter(Objects::nonNull)
                .map(PhotoQualityScore::getScore)
                .orElse(DEFAULT_SCORE);
    }
}
