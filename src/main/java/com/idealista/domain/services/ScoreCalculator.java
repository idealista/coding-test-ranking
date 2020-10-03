package com.idealista.domain.services;

import com.idealista.domain.Ad;
import com.idealista.domain.accumulators.CompleteAdScoreAccumulator;
import com.idealista.domain.accumulators.DescriptionScoreAccumulator;
import com.idealista.domain.accumulators.PicturesScoreAccumulator;

import java.time.Clock;
import java.util.Date;

public final class ScoreCalculator {

    private final PicturesScoreAccumulator picturesScoreAccumulator;
    private final DescriptionScoreAccumulator descriptionScoreAccumulator;
    private final CompleteAdScoreAccumulator completeAdScoreAccumulator;
    private final Clock clock;

    public ScoreCalculator(Clock clock) {
        this.clock = clock;
        this.picturesScoreAccumulator = new PicturesScoreAccumulator();
        this.descriptionScoreAccumulator = new DescriptionScoreAccumulator();
        this.completeAdScoreAccumulator = new CompleteAdScoreAccumulator();
    }

    public Ad execute(Ad ad) {
        final Integer calculatedScore = picturesScoreAccumulator
                .andThen(descriptionScoreAccumulator)
                .andThen(completeAdScoreAccumulator)
                .apply(ad).getScore();

        return getAdWithScore(ad, calculatedScore);
    }

    private Ad getAdWithScore(Ad ad, Integer calculatedScore) {
        if (isIrrelevantScore(calculatedScore)) {
            return ad.withDate(Date.from(clock.instant())).withScore(getFinalScore(calculatedScore));
        } else {
            return ad.withScore(getFinalScore(calculatedScore));
        }
    }

    private boolean isIrrelevantScore(Integer calculatedScore) {
        return getFinalScore(calculatedScore) < 40;
    }

    private int getFinalScore(Integer calculatedScore) {
        return isNegativeScore(calculatedScore) ? 0: calculatedScore;
    }

    private boolean isNegativeScore(final int calculatedScore){
        return calculatedScore < 0;
    }

}
