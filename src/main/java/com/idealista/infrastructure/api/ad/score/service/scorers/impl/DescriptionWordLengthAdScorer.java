package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import com.idealista.infrastructure.api.ad.score.configuration.WordRangeByTypologyConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RequiredArgsConstructor
@Component
public class DescriptionWordLengthScorer implements AdScorer {

    private final WordRangeByTypologyConfiguration wordRangeByTypologyConfiguration;

    @Override
    public Integer getScore(Ad ad) {
        Integer descriptionWordCount = countWords(ad.getDescription());
        return wordRangeByTypologyConfiguration.getWordRangesByAdTypology(ad.getTypology()).stream()
            .filter(wordRange -> wordRange.isWordCountWithinRange(descriptionWordCount))
            .findFirst()
            .orElse(WordRange.builder().score(0).build())
            .getScore();
    }

    private Integer countWords(String description){
        if (isBlank(description)){
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(description);
        return tokens.countTokens();
    }
}
