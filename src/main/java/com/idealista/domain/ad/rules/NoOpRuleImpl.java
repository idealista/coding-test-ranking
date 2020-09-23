package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;

public final class NoOpRuleImpl implements AdCompleteRule {

    public static NoOpRuleImpl INSTANCE = new NoOpRuleImpl();

    private NoOpRuleImpl() {}

    @Override
    public Boolean canApply(Ad ad) {
        return true;
    }

    @Override
    public Boolean isComplete(Ad ad) {
        return true;
    }

}
