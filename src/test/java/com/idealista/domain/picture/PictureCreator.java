package com.idealista.domain.picture;

import com.idealista.util.BaseCreator;

public final class PictureCreator extends BaseCreator {

    public static Picture create(String id, String url, PictureQuality quality) {
        return new Picture(id, url, quality);
    }

    public static Picture create() {
        return create(
                randomUuid(),
                randomUrl(),
                randomElement(PictureQuality.SD, PictureQuality.HD)
        );
    }

}
