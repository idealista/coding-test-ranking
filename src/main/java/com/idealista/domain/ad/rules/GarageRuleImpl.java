package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;

public final class GarageRuleImpl implements AdCompleteRule {

    public static GarageRuleImpl INSTANCE = new GarageRuleImpl();

    private GarageRuleImpl() {}

    @Override
    public Boolean canApply(Ad ad) {
        return AdTypology.GARAGE.equals(ad.getTypology());
    }

    @Override
    public Boolean isComplete(Ad ad) {
        return ad.hasPictures();
    }

}
