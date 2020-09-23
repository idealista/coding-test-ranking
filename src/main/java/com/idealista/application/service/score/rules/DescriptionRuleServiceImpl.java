package com.idealista.application.service.score.rules;

import com.idealista.domain.ad.Ad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DescriptionRuleServiceImpl implements ScoreRuleService {

    private final Integer points;

    public DescriptionRuleServiceImpl(
            @Value("${score.rules.description.points}") Integer points) {
        this.points = points;
    }

    @Override
    public Integer calculateScore(Ad ad) {
        return ad.hasDescription() ? points : 0;
    }

}
