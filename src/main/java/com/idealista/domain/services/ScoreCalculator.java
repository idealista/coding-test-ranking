package com.idealista.domain.services;

import java.util.concurrent.atomic.AtomicInteger;

public final class ScoreCalculator {

    public static final int PICTURE_HD_SCORE = 20;
    public static final int PICTURE_SD_SCORE = 10;

    public Ad execute(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();
        ad.getPictures().forEach(p -> {
            if (hasHighResolutionPicture(p)) {
                scoreCounter.getAndAdd(PICTURE_HD_SCORE);
            } else {
                scoreCounter.getAndAdd(PICTURE_SD_SCORE);
            }
        });
        return ad.withScore(scoreCounter.get());
    }

    private boolean hasHighResolutionPicture(Picture p) {
        return p.getQuality().equals("HD");
    }

}
