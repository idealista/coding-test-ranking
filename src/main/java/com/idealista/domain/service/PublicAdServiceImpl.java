package com.idealista.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealista.domain.mapper.AdVOPublicAdMapper;
import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.api.PublicAd;

@Service
public class PublicAdServiceImpl implements PublicAdService {
	
	@Autowired
	private AdRepository repository;
	
	@Autowired
	private AdVOPublicAdMapper mapper;

	@Override
	public List<PublicAd> getAds() {
		return repository.getAds().stream()
				.filter(ad -> ad.getScore() >= 40)
				.map(ad -> mapper.adVOToPublicAd(ad))
				.collect(Collectors.toList());
	}

}
