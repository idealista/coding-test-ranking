package com.idealista.domain.conditions;

import com.idealista.domain.Ad;

public interface Rule {

    Ad apply(Ad ad);
}
