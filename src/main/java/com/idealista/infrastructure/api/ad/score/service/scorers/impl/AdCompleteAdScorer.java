package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.AdCompleteScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RequiredArgsConstructor
public abstract class AdCompleteAdScorer implements AdScorer {

    private final AdCompleteScoreConfiguration adCompleteScoreConfiguration;

    @Override
    public Integer getScore(Ad ad){
        if (isAdComplete(ad)){
            return adCompleteScoreConfiguration.getScore();
        }
        return 0;
    }

    private boolean isAdComplete(Ad ad){
        return isAdCategoryComplete(ad) && hasAdDescription(ad) && hasAtLeastOnePicture(ad);
    }

    protected abstract boolean isAdCategoryComplete(Ad ad);

    protected boolean hasAdDescription(Ad ad){
        return isNotBlank(ad.getDescription());
    }

    protected boolean hasAtLeastOnePicture(Ad ad){
        return !ad.getAdPictures().isEmpty();
    }
}
