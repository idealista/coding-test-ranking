package com.idealista.infrastructure.api.ad.search.domain;

import static com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality.HD;
import static com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality.SD;

public class AdPictureTestData {

    public static AdPicture.AdPictureBuilder emptyAdPicture(){
        return AdPicture.builder();
    }

    public static AdPicture.AdPictureBuilder hdAdPicture(){
        return emptyAdPicture()
            .quality(HD);
    }

    public static AdPicture.AdPictureBuilder sdAdPicture(){
        return emptyAdPicture()
            .quality(SD);
    }

}
