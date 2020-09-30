package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.KeywordScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordAdScorer implements AdScorer {

    private final KeywordScoreConfiguration keywordScoreConfiguration;
    private static final String ACCENT_UNICODE_REGEX = "\\p{M}";
    private static final String EMPTY_CHARACTER = "";

    @PostConstruct
    public void normalizeKeywords(){
        List<String> normalizedKeywords = keywordScoreConfiguration.getKeywords().stream()
            .map(this::normalizeKeyword)
            .collect(Collectors.toList());
        this.keywordScoreConfiguration.setKeywords(normalizedKeywords);
    }

    private String normalizeKeyword(String keyword){
        String normalizedKeyword = Normalizer.normalize(keyword, Normalizer.Form.NFD);
        return normalizedKeyword.toLowerCase().replaceAll(ACCENT_UNICODE_REGEX, EMPTY_CHARACTER);
    }

    @Override
    public Integer getScore(Ad ad) {
        return getTokenizedDescription(ad.getDescription()).parallelStream()
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
