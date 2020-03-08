package com.idealista.infrastructure.services;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPublicAdsServiceImpl extends GetAdsService implements GetPublicAdsService {

    private static final Integer MIN_SCORE = 40;

    @Autowired
    public GetPublicAdsServiceImpl(AdsRepository repository, ConversionService conversionService) {
        super(repository, conversionService);
    }

    @Override
    public List<PublicAd> get() {
        return adsVORepository.findAll().stream()
                .map(e -> conversionService.convert(e, PublicAd.class))
                .collect(Collectors.toList());
    }
}
