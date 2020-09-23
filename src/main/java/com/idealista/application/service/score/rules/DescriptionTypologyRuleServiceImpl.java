package com.idealista.application.service.score.rules;

import com.idealista.application.util.StringUtil;
import com.idealista.domain.ad.Ad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DescriptionTypologyRuleServiceImpl implements ScoreRuleService {

    private final Integer flatMinPoints;
    private final Integer flatMinWords;
    private final Integer flatFullPoints;
    private final Integer flatFullWords;
    private final Integer chaletFullPoints;
    private final Integer chaletFullWords;

    public DescriptionTypologyRuleServiceImpl(
            @Value("${score.rules.description-typology.flat.min-points}") Integer flatMinPoints,
            @Value("${score.rules.description-typology.flat.min-words}") Integer flatMinWords,
            @Value("${score.rules.description-typology.flat.full-points}") Integer flatFullPoints,
            @Value("${score.rules.description-typology.flat.full-words}") Integer flatFullWords,
            @Value("${score.rules.description-typology.chalet.full-points}") Integer chaletFullPoints,
            @Value("${score.rules.description-typology.chalet.full-words}") Integer chaletFullWords) {
        this.flatMinPoints = flatMinPoints;
        this.flatMinWords = flatMinWords;
        this.flatFullPoints = flatFullPoints;
        this.flatFullWords = flatFullWords;
        this.chaletFullPoints = chaletFullPoints;
        this.chaletFullWords = chaletFullWords;
    }

    @Override
    public Integer calculateScore(Ad ad) {
        final Integer descriptionWordCount = countWords(ad.getDescription());

        if (ad.isFlat()) {

            if (descriptionWordCount >= flatMinWords && descriptionWordCount < flatFullWords) {
                return flatMinPoints;
            } else if (descriptionWordCount >= flatFullWords) {
                return flatFullPoints;
            }

        }
        else if (ad.isChalet()) {

            if (descriptionWordCount >= chaletFullWords) {
                return chaletFullPoints;
            }

        }

        return 0;
    }

    private Integer countWords(String description) {
        final String normalizedDiacritics = StringUtil.normalizeDiacritics(description);
        return StringUtil.stringToWords(normalizedDiacritics).length;
    }

}
