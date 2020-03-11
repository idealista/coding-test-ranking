package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.exceptions.ScoringIncompleteException;

@FunctionalInterface
public interface ScoreAdsService {
    void score() throws ScoringIncompleteException;
}
