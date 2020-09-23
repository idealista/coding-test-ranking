package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;

public class ChaletRuleImpl implements AdCompleteRule {

    public static ChaletRuleImpl INSTANCE = new ChaletRuleImpl();

    private ChaletRuleImpl() {}

    @Override
    public Boolean canApply(Ad ad) {
        return AdTypology.CHALET.equals(ad.getTypology());
    }

    @Override
    public Boolean isComplete(Ad ad) {
        return  ad.hasDescription() &&
                ad.hasPictures() &&
                ad.hasHouseSize() &&
                ad.hasGardenSize();
    }

}
