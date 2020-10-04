package com.idealista.domain.score;

import org.springframework.stereotype.Component;

import com.idealista.infrastructure.persistence.AdVO;

@Component
public class PicturesCriterion implements ScoreCriterion {
	
	private static final int PICTURES_SCORE = 0;
	private static final int NO_PICTURE_SCORE = -10;

	@Override
	public Integer getPartialScore(AdVO ad) {
		if(!ad.hasPictures()) {
			return NO_PICTURE_SCORE;
		} 
		return PICTURES_SCORE;
		
	}

	
	
}
