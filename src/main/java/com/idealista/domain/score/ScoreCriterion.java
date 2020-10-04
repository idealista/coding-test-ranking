package com.idealista.domain.score;

import com.idealista.infrastructure.persistence.AdVO;

public interface ScoreCriterion {

	Integer getPartialScore(AdVO ad);

}
