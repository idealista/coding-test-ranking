package com.idealista.application.service.score;

import com.idealista.application.service.score.rules.ScoreRuleService;
import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final Integer minScore;
    private final Integer maxScore;
    private final Integer minRelevantScore;

    private final AdRepository repository;
    private final Set<ScoreRuleService> scoreRules;

    public ScoreServiceImpl(
            AdRepository repository,
            Set<ScoreRuleService> scoreRules,
            @Value("${score.min}") Integer minScore,
            @Value("${score.max}") Integer maxScore,
            @Value("${score.min-relevant-score}") Integer minRelevantScore) {
        this.repository = repository;
        this.scoreRules = scoreRules;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.minRelevantScore = minRelevantScore;
    }

    public void calculateAdsScore() {
        final List<Ad> allAds = repository.getAllAds();

        allAds.forEach(ad -> {
            final int totalScore = scoreRules.stream()
                    .mapToInt(scoreRule -> scoreRule.calculateScore(ad))
                    .sum();

            ad.updateScore(normalizeScore(totalScore), minRelevantScore);

            repository.saveAd(ad);
        });
    }

    private int normalizeScore(int totalScore) {
        return Math.max(minScore, Math.min(maxScore, totalScore));
    }

}
