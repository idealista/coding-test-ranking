package com.idealista.application.service.rating.impl;

import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import org.springframework.stereotype.Service;

@Service
public class DescriptionRatingRuleService implements RatingRuleService {
    public static final int HAS_DESCRIPTION = 5;

    public static final int MIN_WORDS = 20;
    public static final int MAX_WORDS = 50;

    public static final int HAS_MIN_WORDS = 10;
    public static final int HAS_MAX_WORDS_FLAT = 30;
    public static final int HAS_MAX_WORDS_CHALET = 20;

    @Override
    public int calculate(AdVO ad) {
        if (!ad.hasDescription()) {
            return 0;
        }

        if (ad.isGarage()) {
            return HAS_DESCRIPTION;
        }

        int numberOfWords = ad.getDescription().split(" ").length;

        if (numberOfWords >= MAX_WORDS) {
            return HAS_DESCRIPTION + maxWordsPoints(ad);
        } else if (numberOfWords >= MIN_WORDS) {
            return HAS_DESCRIPTION + HAS_MIN_WORDS;
        }

        return HAS_DESCRIPTION;
    }

    private int maxWordsPoints(AdVO ad) {
        if (ad.isFlat()) {
            return HAS_MAX_WORDS_FLAT;
        } else if (ad.isChalet()) {
            return HAS_MAX_WORDS_CHALET;
        }

        return 0;
    }

}
