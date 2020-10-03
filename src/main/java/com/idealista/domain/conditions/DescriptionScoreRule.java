package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.ExtractScoreValues;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        increaseScoreWhenDescriptionContainsSpecialWords(extractScoreValues.getSpecialWords(), scoreCounter, ad);
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

    private void increaseScoreWhenDescriptionContainsSpecialWords(List<String> specialWords, AtomicInteger scoreCounter, Ad ad) {
        final String description = removeDuplicatedWordsFormDescription(ad);
        final long specialWordCounter = specialWords.stream().map(normalizeWord()).filter(description::contains).count();

        scoreCounter.getAndAdd(Long.valueOf(specialWordCounter * extractScoreValues.getSpecialWordScore()).intValue());
    }

    private String removeDuplicatedWordsFormDescription(Ad ad) {
        return Arrays.stream(ad.getDescription().split(" ")).map(normalizeWord()).distinct().collect(Collectors.joining(" "));
    }

    private Function<String, String> normalizeWord() {
        return word -> {
            final String withOutAccents = StringUtils.stripAccents(word);
            return withOutAccents.toLowerCase();
        };
    }
}
