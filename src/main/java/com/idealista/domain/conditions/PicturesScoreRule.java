package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.ExtractScoreValues;
import com.idealista.domain.Picture;

import java.util.concurrent.atomic.AtomicInteger;

public class PicturesScoreRule implements Rule {

    private final ExtractScoreValues extractScoreValues;

    public PicturesScoreRule(ExtractScoreValues extractScoreValues) {
        this.extractScoreValues = extractScoreValues;
    }

    @Override
    public Ad apply(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();

        if (hasNotPictures(ad)) {
            scoreCounter.getAndAdd(extractScoreValues.getNotPictureScore());
        } else {
            ad.getPictures().forEach(p -> {
                if (hasHighResolutionPicture(p)) {
                    scoreCounter.getAndAdd(extractScoreValues.getHDPictureScore());
                } else {
                    scoreCounter.getAndAdd(extractScoreValues.getSDPictureScore());
                }
            });
        }
        return ad.withScore(scoreCounter.get());
    }

    private boolean hasNotPictures(Ad ad) {
        return ad.getPictures().isEmpty();
    }

    private boolean hasHighResolutionPicture(Picture p) {
        return p.getQuality().equals("HD");
    }
}
