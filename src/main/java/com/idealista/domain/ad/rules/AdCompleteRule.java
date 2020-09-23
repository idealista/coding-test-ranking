package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;

public interface AdCompleteRule {

    Boolean canApply(Ad ad);

    Boolean isComplete(Ad ad);

}
