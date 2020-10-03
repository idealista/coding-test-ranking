package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.ExtractScoreValues;
import com.idealista.domain.Typology;

import java.util.concurrent.atomic.AtomicInteger;

public class CompleteAdScoreRule implements Rule {

    private final ExtractScoreValues extractScoreValues;

    public CompleteAdScoreRule(ExtractScoreValues extractScoreValues) {
        this.extractScoreValues = extractScoreValues;
    }

    @Override
    public Ad apply(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger(getActualScore(ad));

        if (containsPictures(ad)) {
            if (hasFlatTypology(ad) && hasDescription(ad)) {
                if (hasHouseSize(ad)) {
                    scoreCounter.getAndAdd(getCompleteAdScore());
                }
            }
            if (hasChaletTypology(ad) && hasDescription(ad)) {
                if (hasHouseSize(ad) && hasGardenSize(ad)) {
                    scoreCounter.getAndAdd(getCompleteAdScore());
                }
            }
            if (hasGarageTypology(ad)) {
                scoreCounter.getAndAdd(getCompleteAdScore());
            }
        }

        return ad.withScore(scoreCounter.get());
    }

    private int getCompleteAdScore() {
        return extractScoreValues.getCompleteAdScore();
    }

    private Integer getActualScore(Ad ad) {
        return ad.getScore();
    }

    private boolean containsPictures(Ad ad) {
        return ad.getPictures().size() >= 1;
    }

    private boolean hasFlatTypology(Ad ad) {
        return ad.getTypology().equals(Typology.FLAT);
    }


    private boolean hasDescription(Ad ad) {
        return !ad.getDescription().isEmpty();
    }

    private boolean hasChaletTypology(Ad ad) {
        return ad.getTypology().equals(Typology.CHALET);
    }

    private boolean hasGardenSize(Ad ad) {
        return null != ad.getGardenSize();
    }

    private boolean hasHouseSize(Ad ad) {
        return null != ad.getHouseSize();
    }

    private boolean hasGarageTypology(Ad ad) {
        return ad.getTypology().equals(Typology.GARAGE);
    }

}
