package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.ExtractScoreValues;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class DescriptionScoreRule implements Rule {

    private final ExtractScoreValues extractScoreValues;

    public DescriptionScoreRule(ExtractScoreValues extractScoreValues) {
        this.extractScoreValues = extractScoreValues;
    }

    @Override
    public Ad apply(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger(getActualScore(ad));

        if (hasDescription(ad)) {
            scoreCounter.getAndAdd(extractScoreValues.getHasDescriptionScore());
        }
        switch (ad.getTypology()){
            case FLAT:
                if (hasDescriptionLengthBetween(extractScoreValues.getInitialLengthForMediumDescription(), extractScoreValues.getFinalLengthForMediumDescription(), ad)) {
                    scoreCounter.getAndAdd(extractScoreValues.getShortDescriptionScore());
                } else if (hasDescriptionWithLengthGreaterOrEqualsThan(extractScoreValues.getMaximumLengthForFlatDescription(), ad)) {
                    scoreCounter.getAndAdd(extractScoreValues.getLongDescriptionForFlatScore());
                }
                break;
            case CHALET:
                if (hasDescriptionWithLengthGreaterThan(extractScoreValues.getMaximumLengthForChaletDescription(), ad)) {
                    scoreCounter.getAndAdd(extractScoreValues.getLongDescriptionForChaletScore());
                }
        }
        increaseScoreWhenDescriptionContainsSpecialWords(scoreCounter, ad);
        return ad.withScore(scoreCounter.get());
    }

    private Integer getActualScore(Ad ad) {
        return ad.getScore();
    }

    private boolean hasDescription(Ad ad) {
        return !ad.getDescription().isEmpty();
    }

    private boolean hasDescriptionWithLengthGreaterOrEqualsThan(int maximumLength, Ad ad) {
        return ad.getDescription().length() >= maximumLength;
    }

    private boolean hasDescriptionWithLengthGreaterThan(int maximumLength, Ad ad) {
        return ad.getDescription().length() > maximumLength;
    }

    private boolean hasDescriptionLengthBetween(int firstLength, int secondLength, Ad ad) {
        return ad.getDescription().length() >= firstLength && ad.getDescription().length() <= secondLength;
    }

    private void increaseScoreWhenDescriptionContainsSpecialWords(AtomicInteger scoreCounter, Ad ad) {
        final Predicate<String> containsLuminoso = s -> s.equalsIgnoreCase("LUMINOSO");
        final Predicate<String> containsNuevo = s -> s.equalsIgnoreCase("NUEVO");
        final Predicate<String> containsCentrico = s -> s.equalsIgnoreCase("CENTRICO");
        final Predicate<String> containsReformado = s -> s.equalsIgnoreCase("REFORMADO");
        final Predicate<String> containsAtico = s -> s.equalsIgnoreCase("ATICO");
        final long specialWordCounter = Arrays.stream(ad.getDescription().split(" "))
                .distinct()
                .filter(containsLuminoso
                        .or(containsNuevo)
                        .or(containsCentrico)
                        .or(containsReformado)
                        .or(containsAtico))
                .count();
        scoreCounter.getAndAdd(Long.valueOf(specialWordCounter * extractScoreValues.getSpecialWordScore()).intValue());
    }
}
