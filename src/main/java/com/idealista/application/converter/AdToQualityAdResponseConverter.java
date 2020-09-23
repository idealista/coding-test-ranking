package com.idealista.application.converter;

import com.idealista.application.service.picture.PictureService;
import com.idealista.application.service.qualityAd.QualityAdResponse;
import com.idealista.domain.ad.Ad;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdToQualityAdResponseConverter implements Converter<Ad, QualityAdResponse> {

    private final PictureService pictureService;

    public AdToQualityAdResponseConverter(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Override
    public QualityAdResponse convert(Ad ad) {
        final List<String> pictureUrls = pictureService.getPictureUrlsByIds(ad.getPictures());

        final QualityAdResponse qualityAdResponse = new QualityAdResponse();
        qualityAdResponse.setId(ad.getId());
        qualityAdResponse.setTypology(ad.getTypology().toString());
        qualityAdResponse.setDescription(ad.getDescription());
        qualityAdResponse.setPictureUrls(pictureUrls);
        qualityAdResponse.setHouseSize(ad.getHouseSize());
        qualityAdResponse.setGardenSize(ad.getGardenSize());
        qualityAdResponse.setScore(ad.getScore());
        qualityAdResponse.setIrrelevantSince(ad.getIrrelevantSince());

        return qualityAdResponse;
    }
}
