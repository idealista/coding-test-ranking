package com.idealista.infrastructure.services.ads.converters;

import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdVOToPublicAdBaseConverter {

    protected void setFields(AdVO in , PublicAd out ) {
        out.setId(in.getId());
        out.setTypology(in.getTypology());
        out.setDescription(in.getDescription());
        out.setHouseSize(in.getHouseSize());
        out.setGardenSize(in.getGardenSize());

        List<String> pictureUrls = Optional.ofNullable(in.getPictures())
                .map(getListUrlsFunction())
                .orElse(null);
        out.setPictureUrls(pictureUrls);
    }

    private Function<List<PictureVO>, List<String>> getListUrlsFunction() {
        return l -> l.stream().map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
