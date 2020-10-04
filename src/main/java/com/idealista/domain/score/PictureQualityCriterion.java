package com.idealista.domain.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

@Component
public class PictureQualityCriterion implements ScoreCriterion{
	
	private static final int HD_PICTURE_SCORE = 20;
	private static final int SD_PICTURE_SCORE = 10;
	
	@Autowired
	private AdRepository repository;
	
	@Override
	public Integer getPartialScore(AdVO ad) {
		int partialScore = 0;
		for(int picId: ad.getPictures()) {
			PictureVO picture = repository.findPictureById(picId);
			if(picture.isSD()) {
				partialScore += SD_PICTURE_SCORE;
			} else if(picture.isHD()) {
				partialScore += HD_PICTURE_SCORE;
			}
		}
		return partialScore;
	}
}
