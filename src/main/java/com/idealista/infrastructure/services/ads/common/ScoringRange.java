package com.idealista.infrastructure.services.ads.common;

import org.apache.commons.lang3.Range;

public class ScoringRange<T extends Comparable<T>, U> {
    private final Range<T> range;
    private final U scoring;

    public ScoringRange(T t1, T t2, U scoring) {
        this.range = Range.between(t1,t2);
        this.scoring = scoring;
    }

    public U getScoring() {
        return scoring;
    }

    public boolean contains(T element) {
        return range.contains(element);
    }
}
