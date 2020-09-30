package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.PictureAdScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PictureAdScorer implements AdScorer {

    private final PictureAdScoreConfiguration pictureAdScoreConfiguration;

    @Override
    public Integer getScore(Ad ad) {
        if (ad.getAdPictures().isEmpty()){
            return pictureAdScoreConfiguration.getNoPicturePenalty();
        }
        return ad.getAdPictures().stream()
                .mapToInt(picture -> pictureAdScoreConfiguration.getScoreByPictureQuality(picture.getQuality()))
                .sum();
    }
}
