package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.KeywordScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.utils.StringFormatterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static com.idealista.infrastructure.utils.StringFormatterUtils.normalize;

@Component
@RequiredArgsConstructor
public class KeywordAdScorer implements AdScorer {

    private final KeywordScoreConfiguration keywordScoreConfiguration;

    @PostConstruct
    public void normalizeKeywords(){
        List<String> normalizedKeywords = keywordScoreConfiguration.getKeywords().stream()
            .map(StringFormatterUtils::normalize)
            .collect(Collectors.toList());
        this.keywordScoreConfiguration.setKeywords(normalizedKeywords);
    }

    @Override
    public Integer getScore(Ad ad) {
        String normalizedDescription = normalize(ad.getDescription());
        return getTokenizedDescription(normalizedDescription).parallelStream()
                .distinct()
                .filter(keywordScoreConfiguration.getKeywords()::contains)
                .collect(Collectors.toSet())
                .size() * keywordScoreConfiguration.getScore();
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
