package com.idealista.application.service.score.rules;

import com.idealista.application.util.StringUtil;
import com.idealista.domain.ad.Ad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RelevantWordsRuleServiceImpl implements ScoreRuleService {

    private final Integer points;
    private final Set<String> words;

    public RelevantWordsRuleServiceImpl(
            @Value("${score.rules.relevant-words.points}") Integer points,
            @Value("${score.rules.relevant-words.words}") Set<String> words) {
        this.points = points;
        this.words = words;
    }

    @Override
    public Integer calculateScore(Ad ad) {
        String description = StringUtil.normalizeDiacritics(ad.getDescription())
                .trim()
                .toLowerCase();

        return words.stream()
                .map(StringUtil::normalizeDiacritics)
                .map(String::toLowerCase)
                .mapToInt(word -> description.contains(word) ? points : 0)
                .sum();
    }

}
