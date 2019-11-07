package com.idealista.application.service.rating;

import com.idealista.infrastructure.persistence.AdVO;

public interface RatingRuleService {
    int calculate(AdVO ad);
}
