package com.idealista.infrastructure.api.inbound.dto.mapper;

import com.idealista.domain.Ad;
import com.idealista.domain.Picture;
import com.idealista.infrastructure.api.inbound.dto.QualityAd;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
final class AdToQualityAd implements Function<Ad, QualityAd> {

  @Override
  public QualityAd apply(Ad ad) {
      final List<String> picturesUrls = ad.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList());
      return new QualityAd(ad.getId().getValue(), ad.getTypology().name(), ad.getDescription(), picturesUrls, ad.getHouseSize(), ad.getGardenSize(), ad.getScore(), ad.getIrrelevantSince());
  }
}
