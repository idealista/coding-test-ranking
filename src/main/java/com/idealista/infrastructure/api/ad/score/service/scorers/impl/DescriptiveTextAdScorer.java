package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.AdDescriptionScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class DescriptiveTextAdScorer implements AdScorer {

    private final AdDescriptionScoreConfiguration adDescriptionScoreConfiguration;

    @Override
    public Integer getScore(Ad ad) {
        return isBlank(ad.getDescription()) ? 0 : adDescriptionScoreConfiguration.getScore();
    }
}
