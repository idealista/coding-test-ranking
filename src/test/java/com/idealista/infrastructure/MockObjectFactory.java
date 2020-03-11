package com.idealista.infrastructure;

import com.idealista.infrastructure.controllers.QualityAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class MockObjectFactory {

    public static AdVO getAdVOChalet65() {
        return new AdVO(1, "CHALET",
                "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!",
                Arrays.asList(
                        new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"),
                        new PictureVO(2, "http://www.idealista.com/pictures/2", "SD")),
                300, 500, 65, new Date());
    }

    public static AdVO getAdVOGarage5() {
        return  new AdVO(2, "GARAGE", "Garaje en el centro de Albacete",
                Collections.<PictureVO>emptyList(), 300, null, 5, new Date());
    }

    public static AdVO getAdVOFlat85() {
        return  new AdVO(3, "FLAT", "Se trata de un pisazo y es muy céntrico. Esta recien reformado, primeras calidades. Con todo tipo de conexiones por transporte publico. Mejor ver, visitelo sin compromiso.",
                Arrays.asList(
                        new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"),
                        new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")),
                300, null, 85, new Date());
    }

    public static QualityAd getQualityAdChalet65() {
        QualityAd out = new QualityAd();
        out.setId(getAdVOChalet65().getId());
        out.setTypology(getAdVOChalet65().getTypology());
        out.setDescription(getAdVOChalet65().getDescription());
        out.setHouseSize(getAdVOChalet65().getHouseSize());
        out.setGardenSize(getAdVOChalet65().getGardenSize());
        out.setPictureUrls(getAdVOChalet65().getPictures().stream()
                .map(PictureVO::getUrl).collect(Collectors.toList()));
        return out;
    }

    public static QualityAd getQualityAdGarage5() {
        QualityAd out = new QualityAd();
        out.setId(getAdVOGarage5().getId());
        out.setTypology(getAdVOGarage5().getTypology());
        out.setDescription(getAdVOGarage5().getDescription());
        out.setHouseSize(getAdVOGarage5().getHouseSize());
        out.setGardenSize(getAdVOGarage5().getGardenSize());
        out.setPictureUrls(getAdVOGarage5().getPictures().stream()
                .map(PictureVO::getUrl).collect(Collectors.toList()));
        return out;
    }

    public static QualityAd getQualityAdFlat85() {
        QualityAd out = new QualityAd();
        out.setId(getAdVOFlat85().getId());
        out.setTypology(getAdVOFlat85().getTypology());
        out.setDescription(getAdVOFlat85().getDescription());
        out.setHouseSize(getAdVOFlat85().getHouseSize());
        out.setGardenSize(getAdVOFlat85().getGardenSize());
        out.setPictureUrls(getAdVOFlat85().getPictures().stream()
                .map(PictureVO::getUrl).collect(Collectors.toList()));
        return out;
    }

}
