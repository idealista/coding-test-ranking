package com.idealista.application.service.publicAd;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicAdsServiceImpl implements PublicAdsService {

    private final AdRepository repository;
    private final ConversionService conversionService;

    public PublicAdsServiceImpl(
            AdRepository repository,
            ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    public List<PublicAdResponse> getAds() {
        final List<Ad> publicAds = repository.findPublicAds();

        return publicAds.stream()
                .sorted(Comparator.comparingInt(Ad::getScore).reversed())
                .map(ad -> conversionService.convert(ad, PublicAdResponse.class))
                .collect(Collectors.toList());
    }

}
