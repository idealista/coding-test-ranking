package com.idealista.application.service.qualityAd;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityAdsServiceImpl implements QualityAdsService {

    private final AdRepository repository;
    private final ConversionService conversionService;

    public QualityAdsServiceImpl(
            AdRepository repository,
            ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    public List<QualityAdResponse> getAds() {
        final List<Ad> irrelevantAds = repository.findIrrelevantAds();

        return irrelevantAds.stream()
                .map(ad -> conversionService.convert(ad, QualityAdResponse.class))
                .collect(Collectors.toList());
    }

}
