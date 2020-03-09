package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CompleteDataScoring extends AbstractScoring { //implements Scoring

    private static Integer SCORE = 5;

    @Override
    public Integer calculateScoring(AdVO adVO) {
        Integer score = DEFAULT_SCORE;
        if(isNotBlank(adVO.getDescription())) {
            score = SCORE;
        }
        return score;
    }
}
