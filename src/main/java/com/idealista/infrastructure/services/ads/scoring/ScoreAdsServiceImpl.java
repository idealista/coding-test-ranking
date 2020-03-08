package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.persistence.AdsRepository;
import com.idealista.infrastructure.services.ads.common.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.apache.commons.lang3.ObjectUtils.max;
import static org.apache.commons.lang3.ObjectUtils.min;

@Service
public class ScoreAdsServiceImpl extends AdsService implements ScoreAdsService {

    private static final Integer MIN_SCORE = 0;
    private static final Integer MAX_SCORE = 100;

    private Scoring scoring;

    @Autowired
    public ScoreAdsServiceImpl(AdsRepository adsVORepository, ConversionService conversionService) {
        super(adsVORepository, conversionService);
        scoring = Stream.of(new PhotoScoring(),new DescriptionFilledScoring()).reduce(v -> v, Scoring::combine);
    }

    @Override
    public void score() {
        adsVORepository.findAll().stream()
                .map(scoring)
                .map(this::normalize)
                .forEach(adsVORepository::saveOrUpdate);
    }

    private AdVO normalize(AdVO adVO) {
        adVO.setScore(max(adVO.getScore(), MIN_SCORE));
        adVO.setScore(min(adVO.getScore(), MAX_SCORE));
        return adVO;
    }
}
