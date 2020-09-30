package com.idealista.domain.services;

import java.util.concurrent.atomic.AtomicInteger;

public final class ScoreCalculator {

    public static final int PICTURE_HD_SCORE = 20;

    public Ad execute(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();
        ad.getPictures().forEach(p -> scoreCounter.getAndAdd(PICTURE_HD_SCORE));
        return ad.withScore(scoreCounter.get());
    }


}
