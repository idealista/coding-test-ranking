package com.idealista.domain.score;

import org.springframework.stereotype.Component;

import com.idealista.infrastructure.persistence.AdVO;

@Component
public class DescriptionLengthByTypologyCriterion implements ScoreCriterion {

	private static final int SHORT_DESC_SCORE = 0;
	private static final int MID_CHALET_DESC_SCORE = 20;
	private static final int LNG_FLAT_DESC_SCORE = 30;
	private static final int MID_FLAT_DESC_SCORE = 10;
	private static final int SHORT_DESCRIPTION_THRESHOLD = 20;
	private static final int LONG_DESCRIPTION_THRESHOLD = 50;
	
	@Override
	public Integer getPartialScore(AdVO ad) {
		int partialScore = 0;
		if(ad.isFlatAd()) {
			partialScore = getFlatDescriptionScore(ad);
		} else if (ad.isChaletAd()) {
			partialScore = getChaletDescriptionScore(ad);
		}
		return partialScore;
	}
	
	private int getFlatDescriptionScore(AdVO ad) {
		if(ad.getDescriptionWordsLength()>=SHORT_DESCRIPTION_THRESHOLD
				&& ad.getDescriptionWordsLength()<LONG_DESCRIPTION_THRESHOLD) {
			return MID_FLAT_DESC_SCORE;
		} else if (ad.getDescriptionWordsLength() >= LONG_DESCRIPTION_THRESHOLD) {
			return LNG_FLAT_DESC_SCORE;
		}
		return SHORT_DESC_SCORE;
	}

	private int getChaletDescriptionScore(AdVO ad) {
		if(ad.getDescriptionWordsLength() >= LONG_DESCRIPTION_THRESHOLD) {
			return MID_CHALET_DESC_SCORE;
		}
		return SHORT_DESC_SCORE;
	}
	
}
