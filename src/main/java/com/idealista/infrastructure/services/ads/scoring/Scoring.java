package com.idealista.infrastructure.services.ads.scoring;

import com.idealista.infrastructure.entities.AdVO;
import org.apache.commons.lang3.ObjectUtils;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface Scoring extends UnaryOperator<AdVO> {
    default Scoring combine(Scoring after) {
        return value -> after.apply(this.apply(value));
    }
}
