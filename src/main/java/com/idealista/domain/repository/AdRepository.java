package com.idealista.domain.repository;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public interface AdRepository {

	List<AdVO> getAds();
	
	PictureVO findPictureById(int picId);
	
	void saveAd(AdVO ad);

}
