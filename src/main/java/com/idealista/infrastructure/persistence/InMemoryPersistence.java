package com.idealista.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryPersistence {

    private List<AdVO> ads;
    private List<PictureVO> pictures;

    public InMemoryPersistence() {
        ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList(4), 300, null, null, null));
        ads.add(new AdVO(3, "CHALET", "", Arrays.asList(2), 300, null, null, null));
        ads.add(new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo", Arrays.asList(5), 300, null, null, null));
        ads.add(new AdVO(5, "FLAT", "Pisazo,", Arrays.asList(3, 8), 300, null, null, null));
        ads.add(new AdVO(6, "GARAGE", "", Arrays.asList(6), 300, null, null, null));
        ads.add(new AdVO(7, "GARAGE", "Garaje en el centro de Albacete", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Arrays.asList(1, 7), 300, null, null, null));

        pictures = new ArrayList<PictureVO>();
        pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"));
        pictures.add(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD"));
        pictures.add(new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"));
        pictures.add(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD"));
        pictures.add(new PictureVO(5, "http://www.idealista.com/pictures/5", "SD"));
        pictures.add(new PictureVO(6, "http://www.idealista.com/pictures/6", "SD"));
        pictures.add(new PictureVO(7, "http://www.idealista.com/pictures/7", "SD"));
        pictures.add(new PictureVO(8, "http://www.idealista.com/pictures/8", "HD"));
    }

    //TODO crea los métodos que necesites
    public InMemoryPersistence(List<PictureVO> pictures) {
        ads = new ArrayList<>();
        this.pictures = pictures;
    }

    public List<AdVO> findAllAds() {
        return ads;
    }

    public void saveAd(AdVO ad) {
        Optional<AdVO> adOptional = findAdById(ad.getId());

        if (!adOptional.isPresent()) {
            ads.add(ad);
        } else {
            int index = ads.indexOf(adOptional.get());

            ads.set(index, ad);
        }
    }

    public void saveAds(List<AdVO> ads) { ads.forEach(this::saveAd); }

    public Optional<PictureVO> findPictureById(int id) {
        return pictures.stream()
                .filter(pictureVO -> pictureVO.getId() == id)
                .findFirst();
    }

    public Optional<AdVO> findAdById(int id) {
        return ads.stream()
                .filter(adVO -> adVO.getId() == id)
                .findFirst();
    }

    public void setPictures(List<PictureVO> pictures) {
        this.pictures = pictures;
    }

    public void setAds(List<AdVO> ads) {
        this.ads = ads;
    }

    // SELECT * FROM ad WHERE irrelevant_since IS NULL ORDER BY score DESC;
    public List<AdVO> findAllAdsIrrelevantSinceIsNullOrderByScoreDesc() {
        return ads.stream()
                .filter(AdVO::isRelevant)
                .sorted(Comparator.comparing(
                        AdVO::getScore,
                        Comparator.nullsLast(
                                Comparator.naturalOrder())
                        ).reversed()
                )
                .collect(Collectors.toList());
    }

    // SELECT url FROM picture p INNER JOIN ad a on p.ad_id = ad.id;
    public List<String> findAdPicturesUrlById(int adId) {
        List<String> urls = new ArrayList<>();
        Optional<AdVO> optionalAd = findAdById(adId);

        optionalAd.ifPresent(ad -> {
            if (ad.hasPictures()) {
                List<Integer> picturesIds = ad.getPictures();

                for (Integer pictureId : picturesIds) {
                    Optional<PictureVO> pictureOptional = findPictureById(pictureId);

                    pictureOptional.ifPresent(picture -> urls.add(picture.getUrl()));
                }
            }
        });

        return urls;
    }

    // SELECT * FROM ad WHERE irrelevant_since IS NOT NULL;
    public List<AdVO> findIrrelevantAds() {
        return ads.stream()
                .filter(ad -> !ad.isRelevant())
                .collect(Collectors.toList());
    }
}
