package com.idealista.application.service.score.rules;

import com.idealista.domain.ad.Ad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdCompleteRuleServiceImpl implements ScoreRuleService {

    private final Integer points;

    public AdCompleteRuleServiceImpl(
            @Value("${score.rules.completed.points}") Integer points) {
        this.points = points;
    }

    @Override
    public Integer calculateScore(Ad ad) {
        return ad.isComplete() ? points : 0;
    }

}
