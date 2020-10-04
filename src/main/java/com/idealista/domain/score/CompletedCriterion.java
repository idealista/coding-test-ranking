package com.idealista.domain.score;

import org.springframework.stereotype.Component;

import com.idealista.infrastructure.persistence.AdVO;

@Component
public class CompletedCriterion implements ScoreCriterion {
	
	private static final int NO_COMPLETED_SCORE = 0;
	private static final int COMPLETED_SCORE = 40;

	@Override
	public Integer getPartialScore(AdVO ad) {
		if(ad.isCompleted()) {
				return COMPLETED_SCORE;
		}
		return NO_COMPLETED_SCORE;
	}
	
}
