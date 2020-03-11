package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;

import static com.idealista.infrastructure.services.ads.common.AdVOConditions.hasDescription;

public class DescriptionFilledScoring extends AbstractScoring {

    private static Integer SCORE = 5;

    @Override
    protected Integer calculateScoring(AdVO adVO) {
        Integer score = DEFAULT_SCORE;
        if(hasDescription(adVO).getAsBoolean()) {
            score = SCORE;
        }
        return score;
    }
}
