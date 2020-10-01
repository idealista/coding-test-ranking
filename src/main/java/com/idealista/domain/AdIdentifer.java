package com.idealista.domain;

import java.util.Objects;

public final class AdIdentifer {

    private final Integer value;


    public AdIdentifer(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdIdentifer that = (AdIdentifer) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "AdIdentifer{" +
                "value=" + value +
                '}';
    }
}
