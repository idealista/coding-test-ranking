package com.idealista.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum Typology {
    FLAT,
    CHALET,
    GARAGE,
    EMPTY("");

    private String value;

    Typology(String value) {
        this.value = value;
    }

    Typology() {
        this.value = name();
    }

    public String getValue() {
        return value;
    }

    public static Optional<Typology> find(String value) {
        return Stream.of(values()).filter(channel -> channel.name().equalsIgnoreCase(value)).findAny();
    }
}
