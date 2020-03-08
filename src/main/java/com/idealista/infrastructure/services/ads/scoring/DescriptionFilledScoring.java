package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.entities.AdVO;

public class DescriptionFilledScoring implements Scoring {

    @Override
    public AdVO apply(AdVO adVO) {
        return adVO;
    }
}
