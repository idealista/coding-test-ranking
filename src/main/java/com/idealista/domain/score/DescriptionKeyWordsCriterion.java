package com.idealista.domain.score;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.springframework.stereotype.Component;

import com.idealista.infrastructure.persistence.AdVO;

@Component
public class DescriptionKeyWordsCriterion implements ScoreCriterion{
	
	private static final int KEYWORD_SCORE = 5;
	private static final String[] KEYWORDS = 
			new String[]{"luminoso", "nuevo", "centrico", "reformado", "atico"};

	@Override
	public Integer getPartialScore(AdVO ad) {
		int partialScore = 0;
		for(int i = 0; i<KEYWORDS.length; i++) {
			if(isDescriptionContainingKeyWordInsensitive(ad.getDescription(), KEYWORDS[i])) {
				partialScore += KEYWORD_SCORE;
			}
		}
		return partialScore;	}

	private boolean isDescriptionContainingKeyWordInsensitive(String description, String keyword) {
		return Normalizer.normalize(description, Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase().contains(keyword);
	}
	
}
