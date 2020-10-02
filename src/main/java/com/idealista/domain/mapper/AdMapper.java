package com.idealista.domain.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;

public interface AdMapper {

	PublicAd adVOToPublicAd(AdVO ad);
	QualityAd adVOToQualityAd(AdVO ad);

}
