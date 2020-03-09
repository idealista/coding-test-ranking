package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public abstract class AbstractScoring implements Scoring {

    protected static final Integer DEFAULT_SCORE = 0;

    @Override
    public AdVO apply(AdVO adVO) {
        if(nonNull(adVO)) {
            adVO.setScore(defaultIfNull(adVO.getScore(), DEFAULT_SCORE) +
                    defaultIfNull(calculateScoring(adVO), DEFAULT_SCORE));
        }
        return adVO;
    }

    public abstract Integer calculateScoring(AdVO adVO);
}
