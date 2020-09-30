package com.idealista.domain.services;

public final class ScoreCalculator {

    public Ad execute(Ad ad) {
        return ad.withScore(20);
    }
}
