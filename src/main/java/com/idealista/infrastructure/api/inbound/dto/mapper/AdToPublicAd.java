package com.idealista.infrastructure.api.inbound.dto.mapper;

import com.idealista.domain.Ad;
import com.idealista.domain.Picture;
import com.idealista.infrastructure.api.inbound.dto.PublicAd;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
final class AdToPublicAd implements Function<Ad, PublicAd> {

  @Override
  public PublicAd apply(Ad ad) {
      final List<String> picturesUrls = ad.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList());
      return new PublicAd(ad.getId().getValue(), ad.getTypology().name(), ad.getDescription(), picturesUrls, ad.getHouseSize(), ad.getGardenSize());
  }
}
