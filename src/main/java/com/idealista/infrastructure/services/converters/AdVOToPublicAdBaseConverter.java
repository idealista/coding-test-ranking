package com.idealista.infrastructure.services.converters;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;

public class AdVOToPublicAdBaseConverter {

    protected void setFields(AdVO in , PublicAd out ) {
        out.setId(in.getId());
        out.setTypology(in.getTypology());
        out.setDescription(in.getDescription());
        out.setHouseSize(in.getHouseSize());
        out.setGardenSize(in.getGardenSize());
    }
}
