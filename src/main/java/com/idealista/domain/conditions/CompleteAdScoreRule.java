package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.Typology;

import java.util.concurrent.atomic.AtomicInteger;

public class CompleteAdScoreRule implements Rule {

    public static final int COMPLETE_AD_SCORE = 40;

    @Override
    public Ad apply(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger(getActualScore(ad));

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

        return ad.withScore(scoreCounter.get());
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
