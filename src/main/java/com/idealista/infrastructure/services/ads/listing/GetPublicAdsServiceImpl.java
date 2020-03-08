package com.idealista.infrastructure.services.ads.listing;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.persistence.AdsRepository;
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

    private static final Integer MIN_SCORE = 40;

    @Autowired
    public GetPublicAdsServiceImpl(AdsRepository repository, ConversionService conversionService) {
        super(repository, conversionService);
    }

    @Override
    public List<PublicAd> get() {
        return adsVORepository.findAll().stream()
                .filter(a -> compare(a.getScore(), MIN_SCORE) >= 0 )
                .sorted(comparingInt(AdVO::getScore).reversed())
                .map(e -> conversionService.convert(e, PublicAd.class))
                .collect(Collectors.toList());
    }
}
