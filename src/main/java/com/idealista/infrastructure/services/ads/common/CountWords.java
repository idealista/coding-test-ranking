package com.idealista.infrastructure.services.ads.common;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CountWords {

    private final String cad;

    public CountWords(String cad) {
        this.cad = cad;
    }

    public int count() {
        if(isBlank(cad)) {
            return 0;
        }
        return cad.split("[\\pP\\s&&[^']]+").length;
    }


}
