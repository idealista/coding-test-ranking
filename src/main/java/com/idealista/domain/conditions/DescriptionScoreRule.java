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
                if (hasDescriptionLengthBetween20And49(ad)) {
                    scoreCounter.getAndAdd(extractScoreValues.getShortDescriptionScore());
                } else if (hasDescriptionWithLengthGreaterOrEqualsThan50(ad)) {
                    scoreCounter.getAndAdd(extractScoreValues.getLongDescriptionForFlatScore());
                }
                break;
            case CHALET:
                if (hasDescriptionWithLengthGreaterThan50(ad)) {
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

    private boolean hasDescriptionWithLengthGreaterOrEqualsThan50(Ad ad) {
        return ad.getDescription().length() >= 50;
    }

    private boolean hasDescriptionWithLengthGreaterThan50(Ad ad) {
        return ad.getDescription().length() > 50;
    }

    private boolean hasDescriptionLengthBetween20And49(Ad ad) {
        return ad.getDescription().length() >= 20 && ad.getDescription().length() <= 49;
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
