package com.idealista.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealista.domain.repository.AdRepository;
import com.idealista.domain.score.*;
import com.idealista.infrastructure.persistence.AdVO;

@Service
public class ScoreAdServiceImpl implements ScoreAdService {

	@Autowired
	private AdRepository repository;
	
	@Autowired
	private List<ScoreCriterion> criteria;
	
	@Override
	public void calculateScore() {
		List<AdVO> ads = repository.getAds();
		for(AdVO ad: ads) {
			updateAdScore(ad);
			repository.saveAd(ad);
		}
	}

	private void updateAdScore(AdVO ad) {
		for(ScoreCriterion criterion: criteria) {
			ad.updateScore(criterion.getPartialScore(ad));
		}
		
	}

}
