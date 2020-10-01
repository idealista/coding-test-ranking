package com.idealista.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Repository
public class InMemoryPersistence {

    private Map<Integer, AdVO> ads;
    private Map<Integer,PictureVO> pictures;

    public InMemoryPersistence() {
        ads = new HashMap<Integer, AdVO>() {
            {
                put(1, new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", emptyList(), 300, null, null, null));
                put(2, new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", singletonList(4), 300, null, null, null));
                put(3, new AdVO(3, "CHALET", "", singletonList(2), 300, null, null, null));
                put(4, new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo", singletonList(5), 300, null, null, null));
                put(5, new AdVO(5, "FLAT", "Pisazo,", asList(3, 8), 300, null, null, null));
                put(6, new AdVO(6, "GARAGE", "", singletonList(6), 300, null, null, null));
                put(7, new AdVO(7, "GARAGE", "Garaje en el centro de Albacete", emptyList(), 300, null, null, null));
                put(8, new AdVO(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", asList(1, 7), 300, null, null, null));
            }
        };

        pictures = new HashMap<Integer, PictureVO>() {
            {
                put(1, new PictureVO(1, "http://www.idealista.com/pictures/1", "HD"));
                put(2, new PictureVO(2, "http://www.idealista.com/pictures/2", "HD"));
                put(3, new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"));
                put(4, new PictureVO(4, "http://www.idealista.com/pictures/4", "HD"));
                put(5, new PictureVO(5, "http://www.idealista.com/pictures/5", "SD"));
                put(6, new PictureVO(6, "http://www.idealista.com/pictures/6", "SD"));
                put(7, new PictureVO(7, "http://www.idealista.com/pictures/7", "SD"));
                put(8, new PictureVO(8, "http://www.idealista.com/pictures/8", "HD"));
            }
        };
    }

    public AdVO findById(final Integer id) {
        return ads.get(id);
    }

    public List<AdVO> findAllAds() {
        return new ArrayList<>(ads.values());
    }

    public void updateAd(AdVO updatedAd) {
        ads.put(updatedAd.getId(), updatedAd);
    }


    public PictureVO findPictureById(final Integer id) {
        return pictures.get(id);
    }
}
