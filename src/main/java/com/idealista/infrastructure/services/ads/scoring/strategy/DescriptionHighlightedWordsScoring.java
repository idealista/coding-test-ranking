package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;

import java.util.Arrays;
import java.util.List;

import static com.idealista.infrastructure.services.ads.common.AdVOConditions.hasDescription;
import static org.apache.commons.lang3.StringUtils.countMatches;

public class DescriptionHighlightedWordsScoring extends AbstractScoring {

    private static Integer WORD_SCORE = 5;

    private final static List<String> HIGHLIGHTED_WORDS =
            Arrays.asList("LUMINOSO", "NUEVO", "CÉNTRICO", "REFORMADO", "ÁTICO" );

    @Override
    public Integer calculateScoring(AdVO adVO) {
        Integer score = DEFAULT_SCORE;
        if(hasDescription(adVO).getAsBoolean()) {
            score = calculateScoring(adVO.getDescription());
        }
        return score;
    }

    protected Integer calculateScoring(String in) {
        final String description = in.toUpperCase();
        Long totalWords = HIGHLIGHTED_WORDS.stream().filter(description::contains).count();
        return (int) (totalWords * WORD_SCORE);
    }
}
