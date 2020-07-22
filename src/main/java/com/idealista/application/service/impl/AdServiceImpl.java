package com.idealista.application.service.impl;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

  public static final String LUMINOSO = "Luminoso";
  public static final String ATICO = "Ático";
  public static final String REFORMADO = "Reformado";
  public static final String CENTRICO = "Céntrico";
  public static final String NUEVO = "Nuevo";
  public static final String FLAT = "FLAT";
  public static final String CHALET = "CHALET";
  public static final String GARAGE = "GARAGE";
  private AdRepository adRepository;


  @Override
  public List<AdVO> getPublicAds() {
    List<AdVO> calculateAds = calculateAdsWithScore();
    return calculateAds.stream()
        .filter(adVO -> adVO.getScore() > 40)
        .collect(Collectors.toList());
  }

  @Override
  public List<AdVO> getQualityAds() {
    List<AdVO> calculateAds = calculateAdsWithScore();
    return calculateAds.stream()
        .filter(adVO -> adVO.getScore() < 40 && adVO.getIrrelevantSince() != null)
        .collect(Collectors.toList());
  }

  @Override
  public List<AdVO> calculateAdsWithScore() {
    List<AdVO> ads = adRepository.findAdVO();
    if (ads == null || ads.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ads in database");
    }
    return ads.stream().map(ad -> {
      Integer score = calculatePicturesScore(ad) + calculateDescriptionScore(ad);
      ad.setScore(score);
      if (score > 40) {
        // Un anuncio se considera irrelevante si tiene una puntación inferior a 40 puntos.
        ad.setIrrelevantSince(OffsetDateTime.now());
      }
      return ad;
    }).collect(Collectors.toList());

  }

  //  * Que el anuncio tenga un texto descriptivo suma 5 puntos.
  private Integer calculateDescriptionScore(AdVO ad) {
    if (ad != null) {
      pointsForDescriptionPresence(ad);
      pointsForDescriptionSize(ad);
      pointsForKeyWords(ad);
      pointsForCompleteAd(ad);
    }
    return 0;
  }

  //* Que el anuncio esté completo también aporta puntos. Para considerar un anuncio completo este tiene que tener descripción,
  // al menos una foto y los datos particulares de cada tipología,
  // Si el anuncio tiene todos los datos anteriores, proporciona otros 40 puntos.
  private Integer pointsForCompleteAd(AdVO ad) {
    if (ad.getDescription() != null && ad.getPictures() != null
        && !ad.getDescription().isEmpty() && !ad.getPictures().isEmpty()) {
      // en el caso de los pisos tiene que tener también tamaño de vivienda,
      if (ad.getTypology().equalsIgnoreCase(FLAT)
          && ad.getHouseSize() != null && ad.getHouseSize() > 0) {
        return 40;
      }
      // en el de los chalets, tamaño de vivienda y de jardín.
      if (ad.getTypology().equalsIgnoreCase(CHALET)
          && ad.getHouseSize() != null && ad.getHouseSize() > 0
          && ad.getGardenSize() != null && ad.getGardenSize() > 0) {
        return 40;
      }
    }
    // Además, excepcionalmente, en los garajes
    // no es necesario que el anuncio tenga descripción.
    if (ad.getTypology().equalsIgnoreCase(GARAGE)
        && ad.getPictures() != null && !ad.getPictures().isEmpty()) {
      return 40;
    }
    return 0;
  }

  // * Que las siguientes palabras aparezcan en la descripción añaden 5 puntos cada una: Luminoso, Nuevo, Céntrico, Reformado, Ático.
  private Integer pointsForKeyWords(AdVO ad) {
    return (ad.getDescription().contains(LUMINOSO) ? 5 : 0)
        + (ad.getDescription().contains(NUEVO) ? 5 : 0)
        + (ad.getDescription().contains(CENTRICO) ? 5 : 0)
        + (ad.getDescription().contains(REFORMADO) ? 5 : 0)
        + (ad.getDescription().contains(ATICO) ? 5 : 0);
  }

  // * El tamaño de la descripción también proporciona puntos cuando el anuncio es sobre un piso o sobre un chalet.
  private Integer pointsForDescriptionSize(AdVO ad) {
    if (ad.getTypology() != null) {
      // En el caso de los chalets, si tiene mas de 50 palabras, añade 20 puntos.
      if (ad.getTypology().equalsIgnoreCase(CHALET) && ad.getDescription().length() > 50) {
        return 20;
      }
      // En el caso de los pisos, la descripción aporta 10 puntos si tiene entre 20 y 49 palabras
      // o 30 puntos si tiene 50 o mas palabras.
      if (ad.getTypology().equalsIgnoreCase(FLAT)) {
        if (ad.getDescription().length() > 20 && ad.getDescription().length() < 50) {
          return 10;
        } else if (ad.getDescription().length() > 50) {
          return 30;
        }
      }
    }
    return 0;
  }

  private Integer pointsForDescriptionPresence(AdVO ad) {
    if (ad.getDescription() == null) {
      return 0;
    }
    return ad.getDescription().length() > 0 ? 5 : 0;
  }

  private Integer calculatePicturesScore(AdVO ad) {

    //  * Si el anuncio no tiene ninguna foto se restan 10 puntos.
    if (ad.getPictures() == null || ad.getPictures().isEmpty()) {
      return -10;
    }

    return ad.getPictures().stream()
        .map(this::getPictureScore)
        .mapToInt(Integer::intValue)
        .sum();
  }

  //  Cada foto que tenga el anuncio proporciona 20 puntos si es una foto de alta resolución (HD) o 10 si no lo es.
  private Integer getPictureScore(Integer picId) {
    Optional<PictureVO> pic = adRepository.findPictureVOById(picId);
    return pic.map(pictureVO -> pictureVO.getQuality().equalsIgnoreCase("HD") ? 20 : 10).orElse(0);
  }

}
