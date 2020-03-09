package com.idealista.infrastructure.services.ads.common;

import com.idealista.infrastructure.entities.AdVO;

import java.util.function.BooleanSupplier;

import static java.util.Objects.nonNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface AdVOConditions extends BooleanSupplier {

    static AdVOConditions hasPhoto(AdVO adVO) {
        return () -> isNotEmpty(adVO.getPictures());
    }

    static AdVOConditions hasDescription(AdVO adVO) {
        return () ->  isNotBlank(adVO.getDescription());
    }

    static AdVOConditions hasHouseSize(AdVO adVO) {
        return () -> nonNull(adVO.getHouseSize());
    }

    static AdVOConditions hasGardenSize(AdVO adVO) {
        return () -> nonNull(adVO.getGardenSize());
    }

}
