package com.idealista.application.converter;

import com.idealista.application.service.picture.PictureService;
import com.idealista.application.service.publicAd.PublicAdResponse;
import com.idealista.domain.ad.Ad;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdToPublicAdResponseConverter implements Converter<Ad, PublicAdResponse> {

    private final PictureService pictureService;

    public AdToPublicAdResponseConverter(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Override
    public PublicAdResponse convert(Ad ad) {
        final List<String> pictureUrls = pictureService.getPictureUrlsByIds(ad.getPictures());

        final PublicAdResponse publicAdResponse = new PublicAdResponse();
        publicAdResponse.setId(ad.getId());
        publicAdResponse.setTypology(ad.getTypology().toString());
        publicAdResponse.setDescription(ad.getDescription());
        publicAdResponse.setPictureUrls(pictureUrls);
        publicAdResponse.setHouseSize(ad.getHouseSize());
        publicAdResponse.setGardenSize(ad.getGardenSize());

        return publicAdResponse;
    }
}
