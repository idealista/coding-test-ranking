package com.idealista.infrastructure.services.converters;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import org.springframework.core.convert.converter.Converter;

import static java.util.Objects.nonNull;

public class AdVOToPublicAdConverter extends AdVOToPublicAdBaseConverter implements Converter<AdVO, PublicAd> {

    @Override
    public PublicAd convert(AdVO in) {
        PublicAd out = null;
        if(nonNull(in)) {
            out = new PublicAd();
            setFields(in, out);
        }
        return out;
    }
}
