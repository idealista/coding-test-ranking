package com.idealista.application.service.rating.impl;

import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import org.springframework.stereotype.Service;

@Service
public class FieldsRatingRuleService implements RatingRuleService {
    public static final int COMPLETE_FIELDS = 40;

    @Override
    public int calculate(AdVO ad) {
        if (completeChalet(ad) ||
                completeFlat(ad) ||
                completeGarage(ad)) {

            return COMPLETE_FIELDS;
        }

        return 0;
    }

    private boolean completeGarage(AdVO ad) {
        if (!ad.isGarage())
            return false;

        return ad.hasPictures();
    }

    private boolean completeFlat(AdVO ad) {
        if (!ad.isFlat())
            return false;

        return ad.hasPictures() && ad.hasDescription() && ad.hasHouseSize();
    }

    private boolean completeChalet(AdVO ad) {
        if (!ad.isChalet())
            return false;

        return ad.hasPictures() && ad.hasDescription() && ad.hasHouseSize() && ad.hasGardenSize();
    }

}
