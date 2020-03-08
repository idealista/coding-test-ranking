package com.idealista.infrastructure.services;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.controllers.QualityAd;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

public class GetAdsService {

    protected final AdsRepository adsVORepository;
    protected final ConversionService conversionService;

    public GetAdsService(AdsRepository adsVORepository, ConversionService conversionService) {
        this.adsVORepository = adsVORepository;
        this.conversionService = conversionService;
    }

    /*
    @Override
    public List<? extends PublicAd> get(Class<? extends PublicAd.class> clazz) {
        return adsVORepository.findAll().stream()
                .map(e -> conversionService.convert(e, clazz)).collect(Collectors.toList());
    }

     */
}
