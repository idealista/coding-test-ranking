package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.AdCompleteScoreConfiguration;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import org.springframework.stereotype.Component;

@Component
public class FlatAdCompleteAdScorer extends AdCompleteAdScorer {

    public FlatAdCompleteAdScorer(AdCompleteScoreConfiguration adCompleteScoreConfiguration){
        super(adCompleteScoreConfiguration);
    }

    @Override
    protected boolean isAdCategoryComplete(Ad ad) {
        if (ad.isNotFlat()){
            return false;
        }
        return ad.getHouseSize().isPresent();
    }
}
