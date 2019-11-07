package com.idealista.application.service.impl;

import com.idealista.application.service.AdRatingService;
import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AdRatingServiceImpl implements AdRatingService {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 100;

    private InMemoryPersistence inMemoryPersistence;
    private Set<RatingRuleService> ratingRuleServices;

    public AdRatingServiceImpl(InMemoryPersistence inMemoryPersistence, Set<RatingRuleService> ratingRuleServices) {
        this.inMemoryPersistence = inMemoryPersistence;
        this.ratingRuleServices = ratingRuleServices;
    }

    @Override
    public void rateAds() {
        List<AdVO> ads = inMemoryPersistence.findAllAds();

        ads.stream()
                .map(this::rateAd)
                .forEach(ad -> inMemoryPersistence.saveAd(ad));
    }

    private AdVO rateAd(AdVO ad) {
        int score = ratingRuleServices
                .stream()
                .mapToInt(ratingRuleService -> {
                    return ratingRuleService.calculate(ad);
                })
                .sum();

        if (score < MIN_SCORE) {
            score = MIN_SCORE;
        } else if (score > MAX_SCORE) {
            score = MAX_SCORE;
        }

        ad.setScore(score);
        if (ad.isRelevant()) {
            ad.setIrrelevantSince(new Date());
        }

        return ad;
    }
}
