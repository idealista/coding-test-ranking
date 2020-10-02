package com.idealista.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.persistence.AdVO;

@Component
public class AdVOPublicAdMapperImpl implements AdVOPublicAdMapper {

	@Autowired
	private AdRepository repository;

	@Override
	public PublicAd adVOToPublicAd(AdVO ad) {
		PublicAd publicAd = new PublicAd();
		publicAd.setId(ad.getId());
		publicAd.setTypology(ad.getTypology());
		publicAd.setDescription(ad.getDescription());
		publicAd.setPictureUrls(getPictureUrls(ad));
		publicAd.setHouseSize(ad.getHouseSize());
		publicAd.setGardenSize(ad.getGardenSize());
		return publicAd;
	}

	private List<String> getPictureUrls(AdVO ad) {
		List<String> pictureUrls = new ArrayList<String>();
		if(ad.getPictures()!=null) {
			for(int picId: ad.getPictures()) {
				pictureUrls.add(repository.findPictureById(picId).getUrl());
			}
		}
		return pictureUrls;
	}

}
