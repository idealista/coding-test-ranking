package com.idealista.domain.services;

import com.idealista.domain.Ad;
import com.idealista.domain.conditions.CompleteAdScoreRule;
import com.idealista.domain.conditions.DescriptionScoreRule;
import com.idealista.domain.conditions.PicturesScoreRule;
import com.idealista.domain.conditions.Rule;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ScoreCalculator {

    private final List<Rule> accumulators = new ArrayList<Rule>(){
        {
            add(new PicturesScoreRule());
            add(new DescriptionScoreRule());
            add(new CompleteAdScoreRule());
        }
    };
    private final Clock clock;

    public ScoreCalculator(Clock clock) {
        this.clock = clock;
    }

    public Ad execute(Ad ad) {
        for (Rule rule : accumulators) {
            ad = rule.apply(ad);
        }
        return getAdWithScore(ad);
    }

    private Ad getAdWithScore(Ad ad) {
        if (isIrrelevantScore(ad.getScore())) {
            return ad.withDate(Date.from(clock.instant())).withScore(getFinalScore(ad.getScore()));
        } else {
            return ad.withScore(getFinalScore(ad.getScore()));
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
