package com.idealista.domain.score;

import org.springframework.stereotype.Component;

import com.idealista.infrastructure.persistence.AdVO;

@Component
public class DescriptionCriterion implements ScoreCriterion {
	
	private static final int NO_DESCRIPTION_SCORE = 0;
	private static final int DESCRIPTION_SCORE = 5;

	@Override
	public Integer getPartialScore(AdVO ad) {
		if(ad.hasDescription()) {
			return DESCRIPTION_SCORE;
		}
		return NO_DESCRIPTION_SCORE;
	}

}