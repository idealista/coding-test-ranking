package com.idealista.domain.mothers;

import com.idealista.domain.services.Ad;
import com.idealista.domain.services.AdIdentifer;
import com.idealista.domain.services.Picture;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class AdMother {

    public static Ad createAdWithoutPictures() {
        return new Ad(new AdIdentifer(1), "FLAT", null, null, null, null, null, null);
    }

    public static Ad createAdWithASingleHDPicture() {
        return new Ad(new AdIdentifer(1), "FLAT", null, Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdWithASingleSDPicture() {
        return new Ad(new AdIdentifer(1), "FLAT", null, Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "SD")), null, null, null, null);
    }

    public static Ad createAdWithAThreeHDPictures() {
        return new Ad(new AdIdentifer(1), "FLAT", null, asList(new Picture(1, "http://this-is-a-url.com", "HD"),
                new Picture(2, "http://this-is-a-url.com", "HD"), new Picture(3, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdWithDescription(){
        return new Ad(new AdIdentifer(1), null, "This is a description", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdOfFlatTypologyWithShortDescription(){
        return new Ad(new AdIdentifer(1), "FLAT", "This is a description", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdOfFlatTypologyWithShortDescriptionWithoutPictures(){
        return new Ad(new AdIdentifer(1), "FLAT", "This is a description", null, null, null, null, null);
    }

    public static Ad createAdOfFlatTypologyWithLongDescription(){
        return new Ad(new AdIdentifer(1), "FLAT", "This is a description too much long to check the score calculator", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdOfChaletTypologyWithLongDescription(){
        return new Ad(new AdIdentifer(1), "CHALET", "This is a description too much long to check the score calculator", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdWithSpecialWordInDescription(){
        return new Ad(new AdIdentifer(1), "FLAT", "This is so luminoso", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createAdWithMultipleSpecialWordsInDescription(){
        return new Ad(new AdIdentifer(1), "FLAT", "Contains luminoso and reformado", Collections.singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null);
    }

    public static Ad createCompleteAdWithFlatTypology() {
        return new Ad(new AdIdentifer(1), "FLAT", "This is a complete ad description", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), 70, null, null, null);
    }

    public static Ad createCompleteAdWithChaletTypology() {
        return new Ad(new AdIdentifer(1), "CHALET", "This is a complete ad description", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), 70, 25, null, null);
    }

    public static Ad createCompleteAdWithGarageTypology() {
        return new Ad(new AdIdentifer(1), "GARAGE", null, singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), 70, 25, null, null);
    }
}
