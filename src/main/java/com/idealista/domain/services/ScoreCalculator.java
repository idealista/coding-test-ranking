package com.idealista.domain.services;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class ScoreCalculator {

    public static final int PICTURE_HD_SCORE = 20;
    public static final int PICTURE_SD_SCORE = 10;

    public Ad execute(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();
        calculateScoreForPictures(scoreCounter).andThen(calculateScoreForDescription(scoreCounter)).accept(ad);

        return ad.withScore(scoreCounter.get());
    }

    public Consumer<Ad> calculateScoreForPictures(final AtomicInteger scoreCounter){
        return ad -> ad.getPictures().forEach(p -> {
            //TODO add condition for ad without pictures
            if (hasHighResolutionPicture(p)) {
                scoreCounter.getAndAdd(PICTURE_HD_SCORE);
            } else {
                scoreCounter.getAndAdd(PICTURE_SD_SCORE);
            }
        });
    }

    public Consumer<Ad> calculateScoreForDescription(final AtomicInteger scoreCounter){
        return ad -> {
            if (hasDescription(ad)) {
                scoreCounter.getAndAdd(5);
            }
        };
    }

    private boolean hasDescription(Ad ad) {
        return !ad.getDescription().isEmpty();
    }

    private boolean hasHighResolutionPicture(Picture p) {
        return p.getQuality().equals("HD");
    }

}
