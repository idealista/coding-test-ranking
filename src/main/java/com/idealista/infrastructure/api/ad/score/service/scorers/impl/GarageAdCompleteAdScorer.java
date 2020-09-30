package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.AdCompleteScoreConfiguration;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import org.springframework.stereotype.Component;

@Component
public class GarageAdCompleteAdScorer extends AdCompleteAdScorer {

    public GarageAdCompleteAdScorer(AdCompleteScoreConfiguration adCompleteScoreConfiguration){
        super(adCompleteScoreConfiguration);
    }

    @Override
    protected boolean hasAdDescription(Ad ad){
        return true;
    }

    @Override
    protected boolean isAdCategoryComplete(Ad ad) {
        if (ad.isNotGarage()){
            return false;
        }
        return true;
    }
}
