package com.idealista.domain.conditions;

import com.idealista.domain.Ad;
import com.idealista.domain.Picture;

import java.util.concurrent.atomic.AtomicInteger;

public class PicturesScoreRule implements Rule {

    public static final int PICTURE_HD_SCORE = 20;
    public static final int PICTURE_SD_SCORE = 10;
    public static final int NEGATIVE_SCORE_FOR_HAS_NOT_PICTURES = -10;


    @Override
    public Ad apply(Ad ad) {
        final AtomicInteger scoreCounter = new AtomicInteger();

        if (hasNotPictures(ad)) {
            scoreCounter.getAndAdd(NEGATIVE_SCORE_FOR_HAS_NOT_PICTURES);
        } else {
            ad.getPictures().forEach(p -> {
                if (hasHighResolutionPicture(p)) {
                    scoreCounter.getAndAdd(PICTURE_HD_SCORE);
                } else {
                    scoreCounter.getAndAdd(PICTURE_SD_SCORE);
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
