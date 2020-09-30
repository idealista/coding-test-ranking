package com.idealista.domain.services;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class ScoreCalculator {

    public static final int PICTURE_HD_SCORE = 20;
    public static final int PICTURE_SD_SCORE = 10;
    public static final int SPECIAL_WORD_FACTOR = 5;

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
            if (hasFlatTypology(ad) && hasDescriptionLengthBetween20And49(ad)) {
                scoreCounter.getAndAdd(10);
            }
            if (hasFlatTypology(ad) && hasDescriptionWithLengthGreaterOrEqualsThan50(ad)) {
                scoreCounter.getAndAdd(30);
            }
            if (hasChaletTypology(ad) && hasDescriptionWithLengthGreaterThan50(ad)) {
                scoreCounter.getAndAdd(20);
            }
            increaseScoreWhenDescriptionContainsSpecialWords(scoreCounter, ad);
        };
    }

    private void increaseScoreWhenDescriptionContainsSpecialWords(AtomicInteger scoreCounter, Ad ad) {
        final long specialWordCounter = Arrays.stream(ad.getDescription().split(" "))
                .distinct()
                .filter(s -> s.equalsIgnoreCase("LUMINOSO"))
                .count();
        scoreCounter.getAndAdd(Long.valueOf(specialWordCounter * SPECIAL_WORD_FACTOR).intValue());
    }

    private boolean hasDescriptionWithLengthGreaterOrEqualsThan50(Ad ad) {
        return ad.getDescription().length() >= 50;
    }

    private boolean hasDescriptionWithLengthGreaterThan50(Ad ad) {
        return ad.getDescription().length() > 50;
    }

    private boolean hasDescriptionLengthBetween20And49(Ad ad) {
        return ad.getDescription().length() >= 20 && ad.getDescription().length() <= 49;
    }

    private boolean hasFlatTypology(Ad ad) {
        return ad.getTypology().equals("FLAT");
    }

    private boolean hasChaletTypology(Ad ad) {
        return ad.getTypology().equals("CHALET");
    }

    private boolean hasDescription(Ad ad) {
        return !ad.getDescription().isEmpty();
    }

    private boolean hasHighResolutionPicture(Picture p) {
        return p.getQuality().equals("HD");
    }

}
