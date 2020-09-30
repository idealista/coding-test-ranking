package com.idealista.domain.services;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ScoreCalculator {

    public static final int PICTURE_HD_SCORE = 20;
    public static final int PICTURE_SD_SCORE = 10;
    public static final int SPECIAL_WORD_FACTOR = 5;
    public static final int COMPLETE_AD_SCORE = 40;

    public Ad execute(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();
        calculateScoreForPictures(scoreCounter)
                .andThen(calculateScoreForDescription(scoreCounter))
                .andThen(calculateScoreForCompleteAdsWithFlatTypology(scoreCounter))
                .accept(ad);
        return ad.withScore(scoreCounter.get());
    }

    public Consumer<Ad> calculateScoreForCompleteAdsWithFlatTypology(final AtomicInteger scoreCounter) {
        return ad -> {
            if (containsPictures(ad)) {
                if (hasFlatTypology(ad) && hasDescription(ad)) {
                    if (hasHouseSize(ad)) {
                        scoreCounter.getAndAdd(COMPLETE_AD_SCORE);
                    }
                }
                if (hasChaletTypology(ad) && hasDescription(ad)) {
                    if (hasHouseSize(ad) && hasGardenSize(ad)) {
                        scoreCounter.getAndAdd(COMPLETE_AD_SCORE);
                    }
                }
                if (hasGarageTypology(ad)) {
                    scoreCounter.getAndAdd(COMPLETE_AD_SCORE);
                }
            }
        };
    }

    private boolean hasGardenSize(Ad ad) {
        return null != ad.getGardenSize();
    }

    private boolean hasHouseSize(Ad ad) {
        return null != ad.getHouseSize();
    }

    private boolean containsPictures(Ad ad) {
        return ad.getPictures().size() >= 1;
    }

    public Consumer<Ad> calculateScoreForPictures(final AtomicInteger scoreCounter) {
        return ad -> {
            if (ad.getPictures().isEmpty()) {
                scoreCounter.updateAndGet(score -> score - 10);
            } else {
                ad.getPictures().forEach(p -> {
                    if (hasHighResolutionPicture(p)) {
                        scoreCounter.getAndAdd(PICTURE_HD_SCORE);
                    } else {
                        scoreCounter.getAndAdd(PICTURE_SD_SCORE);
                    }
                });
            }
        };
    }

    public Consumer<Ad> calculateScoreForDescription(final AtomicInteger scoreCounter) {
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
        final Predicate<String> containsLuminoso = s -> s.equalsIgnoreCase("LUMINOSO");
        final Predicate<String> containsNuevo = s -> s.equalsIgnoreCase("NUEVO");
        final Predicate<String> containsCentrico = s -> s.equalsIgnoreCase("CENTRICO");
        final Predicate<String> containsReformado = s -> s.equalsIgnoreCase("REFORMADO");
        final Predicate<String> containsAtico = s -> s.equalsIgnoreCase("ATICO");
        final long specialWordCounter = Arrays.stream(ad.getDescription().split(" "))
                .distinct()
                .filter(containsLuminoso.or(containsNuevo).or(containsCentrico).or(containsReformado).or(containsAtico))
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

    private boolean hasGarageTypology(Ad ad) {
        return ad.getTypology().equals("GARAGE");
    }

    private boolean hasDescription(Ad ad) {
        return !ad.getDescription().isEmpty();
    }

    private boolean hasHighResolutionPicture(Picture p) {
        return p.getQuality().equals("HD");
    }

}
