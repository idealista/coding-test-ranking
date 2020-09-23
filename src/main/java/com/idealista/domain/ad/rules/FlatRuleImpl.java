package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;

public final class FlatRuleImpl implements AdCompleteRule {

    public static FlatRuleImpl INSTANCE = new FlatRuleImpl();

    private FlatRuleImpl() {}

    @Override
    public Boolean canApply(Ad ad) {
        return AdTypology.FLAT.equals(ad.getTypology());
    }

    @Override
    public Boolean isComplete(Ad ad) {
        return  ad.hasDescription() &&
                ad.hasPictures() &&
                ad.hasHouseSize();
    }

}
