package com.idealista.domain.services;

import com.idealista.domain.Ad;
import com.idealista.domain.accumulators.CompleteAdScoreAccumulator;
import com.idealista.domain.accumulators.DescriptionScoreAccumulator;
import com.idealista.domain.accumulators.PicturesScoreAccumulator;

public final class ScoreCalculator {

    private final PicturesScoreAccumulator picturesScoreAccumulator;
    private final DescriptionScoreAccumulator descriptionScoreAccumulator;
    private final CompleteAdScoreAccumulator completeAdScoreAccumulator;

    public ScoreCalculator() {
        this.picturesScoreAccumulator = new PicturesScoreAccumulator();
        this.descriptionScoreAccumulator = new DescriptionScoreAccumulator();
        this.completeAdScoreAccumulator = new CompleteAdScoreAccumulator();
    }

    public Ad execute(Ad ad) {
        final Integer calculatedScore = picturesScoreAccumulator
                .andThen(descriptionScoreAccumulator)
                .andThen(completeAdScoreAccumulator)
                .apply(ad).getScore();

        return ad.withScore(getFinalScore(calculatedScore));
    }

    private int getFinalScore(Integer calculatedScore) {
        return isNegativeScore(calculatedScore) ? 0: calculatedScore;
    }

    private boolean isNegativeScore(final int calculatedScore){
        return calculatedScore < 0;
    }

}
