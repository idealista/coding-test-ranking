package com.idealista.util;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class BaseCreator {

    private static final Faker faker = Faker.instance(Locale.getDefault());

    public static Faker faker() {
        return faker;
    }

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Integer randomNumber() {
        return faker.number().randomDigitNotZero();
    }

    public static String randomWord() {
        return faker.lorem().word();
    }

    public static String randomSentence() {
        return faker.lorem().sentence();
    }

    public static String randomUrl() {
        return faker.internet().url();
    }

    public static Date randomDate() {
        final TimeUnit[] values = TimeUnit.values();
        final TimeUnit randomTimeUnit = values[faker.number().numberBetween(3, values.length - 1)]; // From Seconds to Days

        return randomNumber() % 2 == 0
                ? faker().date().past(faker.number().randomDigitNotZero(), randomTimeUnit)
                : faker().date().future(faker.number().randomDigitNotZero(), randomTimeUnit);
    }

    @SafeVarargs
    public static <T> T randomElement(T... elements) {
        Random rand = new Random();
        return elements[rand.nextInt(elements.length)];
    }

    public static <T> List<T> randomList(Integer size, Supplier<T> creator) {
        List<T> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(creator.get());
        }

        return list;
    }

    public static <T> List<T> randomList(Supplier<T> creator) {
        return randomList(randomNumber(), creator);
    }

}
