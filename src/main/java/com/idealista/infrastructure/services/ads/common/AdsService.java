package com.idealista.infrastructure.services.ads.common;

import com.idealista.infrastructure.persistence.AdsRepository;
import org.springframework.core.convert.ConversionService;

public class AdsService {

    protected final AdsRepository adsVORepository;
    protected final ConversionService conversionService;

    public AdsService(AdsRepository adsVORepository, ConversionService conversionService) {
        this.adsVORepository = adsVORepository;
        this.conversionService = conversionService;
    }
}
