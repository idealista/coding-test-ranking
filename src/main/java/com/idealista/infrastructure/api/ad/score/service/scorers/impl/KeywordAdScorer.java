package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.KeywordScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static com.idealista.infrastructure.utils.StringFormatterUtils.normalize;

@Component
@RequiredArgsConstructor
public class KeywordAdScorer implements AdScorer {

    private final KeywordScoreConfiguration keywordScoreConfiguration;

    @Override
    public Integer getScore(Ad ad) {
        String normalizedDescription = normalize(ad.getDescription());
        Long matchingKeywordsCount = getTokenizedDescription(normalizedDescription).parallelStream()
                .distinct()
                .filter(keywordScoreConfiguration.getKeywords()::contains)
                .count();
        return matchingKeywordsCount.intValue() * keywordScoreConfiguration.getScore();
    }

    private List<String> getTokenizedDescription(String description){
        StringTokenizer tokens = new StringTokenizer(description);
        List<String> descriptionTokens = new ArrayList<>(tokens.countTokens());
        while (tokens.hasMoreTokens()){
            descriptionTokens.add(tokens.nextToken());
        }
        return descriptionTokens;
    }
}
