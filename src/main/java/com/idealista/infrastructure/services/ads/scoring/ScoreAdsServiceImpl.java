package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.exceptions.ScoringIncompleteException;
import com.idealista.infrastructure.persistence.AdsRepository;
import com.idealista.infrastructure.services.ads.common.AdVOConditions;
import com.idealista.infrastructure.services.ads.common.AdsService;
import com.idealista.infrastructure.services.ads.scoring.strategy.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

import static org.apache.commons.lang3.ObjectUtils.*;

@Service
public class ScoreAdsServiceImpl extends AdsService implements ScoreAdsService {

    private static final Integer MIN_SCORE = 0;
    private static final Integer MAX_SCORE = 100;

    private Scoring scoring = Stream.<Scoring>of(new DescriptionFilledScoring(),
            new DescriptionByTypologyScoring(),
            new DescriptionHighlightedWordsScoring(),
            new PhotoScoring(),
            new CompleteDataScoring()).reduce(adVO -> {adVO.setScore(null);return adVO;}, Scoring::combine);

    @Autowired
    public ScoreAdsServiceImpl(AdsRepository adsVORepository, ConversionService conversionService) {
        super(adsVORepository, conversionService);
    }

    @Override
    public void score() throws ScoringIncompleteException {
        try {
            adsVORepository.findAll().stream()
                    .map(scoring)
                    .map(this::normalizeScore)
                    .map(this::applyIrrelevantSince)
                    .forEach(adsVORepository::saveOrUpdate);

        } catch (Exception e) {
            throw new ScoringIncompleteException("Error during scoring", e.getCause());
        }
    }

    private AdVO normalizeScore(AdVO adVO) {
        adVO.setScore(max(adVO.getScore(), MIN_SCORE));
        adVO.setScore(min(adVO.getScore(), MAX_SCORE));
        return adVO;
    }

    private AdVO applyIrrelevantSince(AdVO adVO) {
        Date irrelevantSince = null;
        if(!AdVOConditions.isRelevant(adVO).getAsBoolean()) {
            irrelevantSince = defaultIfNull(adVO.getIrrelevantSince(), new Date());
        }
        adVO.setIrrelevantSince(irrelevantSince);
        return adVO;
    }
}
