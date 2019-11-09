package com.idealista.application.service.impl;

import com.idealista.application.service.AdRatingService;
import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdRatingServiceImpl implements AdRatingService {
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 100;
    public static final int RELEVANT_SCORE = 40;

    private InMemoryPersistence inMemoryPersistence;
    private Set<RatingRuleService> ratingRuleServices;

    public AdRatingServiceImpl(InMemoryPersistence inMemoryPersistence, Set<RatingRuleService> ratingRuleServices) {
        this.inMemoryPersistence = inMemoryPersistence;
        this.ratingRuleServices = ratingRuleServices;
    }

    @Override
    public void rate() {
        List<AdVO> ads = inMemoryPersistence.findAllAds();

        List<AdVO> ratedAds = rateAds(ads);

        inMemoryPersistence.saveAds(ratedAds);
    }

    private List<AdVO> rateAds(List<AdVO> ads) {
        return ads.stream()
                .map(this::rateAd)
                .collect(Collectors.toList());
    }

    private AdVO rateAd(AdVO ad) {
        int score = calculateScore(ad);

        return setAdScore(ad, score);
    }

    private AdVO setAdScore(AdVO ad, int score) {
        if (ad.isRelevant() && score < RELEVANT_SCORE) {
            ad.setIrrelevantSince(new Date());
        } else if (score >= RELEVANT_SCORE) {
            ad.setIrrelevantSince(null);
        }

        ad.setScore(score);

        return ad;
    }

    private int calculateScore(AdVO ad) {
        int score = ratingRuleServices
                .stream()
                .mapToInt(ratingRuleService -> ratingRuleService.calculate(ad))
                .sum();

        return getScoreBetweenLimits(score);
    }

    private int getScoreBetweenLimits(int score) {
        return Math.max(
                Math.min(score, MAX_SCORE),
                MIN_SCORE);
    }
}
