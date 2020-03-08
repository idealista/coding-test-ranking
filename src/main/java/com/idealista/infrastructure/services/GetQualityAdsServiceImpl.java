package com.idealista.infrastructure.services;

import com.idealista.infrastructure.controllers.QualityAd;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetQualityAdsServiceImpl extends GetAdsService implements GetQualityAdsService {

    @Autowired
    public GetQualityAdsServiceImpl(AdsRepository repository, ConversionService conversionService) {
        super(repository, conversionService);
    }

    @Override
    public List<QualityAd> get() {
        return adsVORepository.findAll().stream()
                .map(e -> conversionService.convert(e, QualityAd.class)).collect(Collectors.toList());
    }
}
