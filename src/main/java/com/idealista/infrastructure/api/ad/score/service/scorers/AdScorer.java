package com.idealista.infrastructure.api.ad.score.service.scorers;

import com.idealista.infrastructure.api.ad.search.domain.Ad;

@FunctionalInterface
public interface AdScorer {

    Integer getScore(Ad ad);

}
