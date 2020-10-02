package com.idealista.domain.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.persistence.AdVO;

public interface AdVOPublicAdMapper {

	PublicAd adVOToPublicAd(AdVO ad);

}
