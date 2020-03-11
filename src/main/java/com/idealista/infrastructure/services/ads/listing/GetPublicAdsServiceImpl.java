package com.idealista.infrastructure.services.ads.listing;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.persistence.AdsRepository;
import com.idealista.infrastructure.services.ads.common.AdVOConditions;
import com.idealista.infrastructure.services.ads.common.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static org.apache.commons.lang3.ObjectUtils.compare;

@Service
public class GetPublicAdsServiceImpl extends AdsService implements GetPublicAdsService {

    @Autowired
    public GetPublicAdsServiceImpl(AdsRepository repository, ConversionService conversionService) {
        super(repository, conversionService);
    }

    @Override
    public List<PublicAd> get() {
        return adsVORepository.findAll().stream()
                .filter(a-> AdVOConditions.isRelevant(a).getAsBoolean())
                .sorted(comparingInt(AdVO::getScore).reversed())
                .map(e -> conversionService.convert(e, PublicAd.class))
                .collect(Collectors.toList());
    }
}
