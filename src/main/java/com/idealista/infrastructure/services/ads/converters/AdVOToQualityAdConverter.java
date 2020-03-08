package com.idealista.infrastructure.services.ads.converters;

import com.idealista.infrastructure.controllers.QualityAd;
import com.idealista.infrastructure.entities.AdVO;
import org.springframework.core.convert.converter.Converter;

import static java.util.Objects.nonNull;

public class AdVOToQualityAdConverter extends AdVOToPublicAdBaseConverter implements Converter<AdVO, QualityAd> {

    @SuppressWarnings("ConstantConditions")
    @Override
    public QualityAd convert(AdVO in) {
        QualityAd out = null;
        if(nonNull(in)) {
            out = new QualityAd();
            setFields(in, out);
            out.setScore(in.getScore());
            out.setIrrelevantSince(in.getIrrelevantSince());
        }
        return out;
    }
}
