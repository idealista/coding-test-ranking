package com.idealista.infrastructure.api.ad.score.service.impl;

import com.idealista.infrastructure.api.ad.score.configuration.AdScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.AdScoreService;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.adapter.AdSearchRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.adapter.AdUpdateRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.IRRELEVANT;

@Service
@RequiredArgsConstructor
public class AdScoreServiceImpl implements AdScoreService {

    private final List<AdScorer> adScorers;
    private final AdScoreConfiguration adScoreConfiguration;
    private final AdSearchRepositoryAdapter adSearchRepositoryAdapter;
    private final AdUpdateRepositoryAdapter adUpdateRepositoryAdapter;

    @Override
    public void calculateAdsScore() {
        List<Ad> ads = adSearchRepositoryAdapter.getAllAds();
        ads.forEach(this::setAdQuality);
        adUpdateRepositoryAdapter.updateAll(ads);
    }

    private void setAdQuality(Ad ad){
        Integer score = calculateAdScore(ad);
        ad.setScore(adScoreConfiguration.clampScore(score));
        AdQuality adQuality = adScoreConfiguration.getAdQuality(score);
        ad.setAdQuality(adQuality);
        if (adQuality.equals(IRRELEVANT)){
            ad.setIrrelevantSince(Optional.of(Date.from(Instant.now())));
        }
    }

    private Integer calculateAdScore(Ad ad) {
        return adScorers.parallelStream()
            .mapToInt(adScorer -> adScorer.getScore(ad))
            .sum();
    }
}
