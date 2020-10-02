package com.idealista.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.api.QualityAd;

@Service
public class QualityAdServiceImpl implements QualityAdService {
	
	@Autowired
	private AdRepository repository;
	
	@Autowired
	private AdMapper mapper;

	@Override
	public List<QualityAd> getAds() {
		return repository.getAds().stream()
				.filter(ad -> !ad.isRelevant())
				.map(ad -> mapper.adVOToQualityAd(ad))
				.collect(Collectors.toList());
	}

}
