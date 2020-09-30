package com.idealista.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryPersistence {

    private List<AdVO> ads;
    private List<PictureVO> pictures;

    public InMemoryPersistence() {
        ads = new ArrayList<AdVO>();
        ads.add(new AdVO(0, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(1, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList(3), 300, null, null, null));
        ads.add(new AdVO(2, "CHALET", "", Arrays.asList(1), 300, null, null, null));
        ads.add(new AdVO(3, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo", Arrays.asList(4), 300, null, null, null));
        ads.add(new AdVO(4, "FLAT", "Pisazo,", Arrays.asList(2, 7), 300, null, null, null));
        ads.add(new AdVO(5, "GARAGE", "", Arrays.asList(5), 300, null, null, null));
        ads.add(new AdVO(6, "GARAGE", "Garaje en el centro de Albacete", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(7, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Arrays.asList(0, 6), 300, null, null, null));

        pictures = new ArrayList<PictureVO>();
        pictures.add(new PictureVO(0, "http://www.idealista.com/pictures/1", "SD"));
        pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/2", "HD"));
        pictures.add(new PictureVO(2, "http://www.idealista.com/pictures/3", "SD"));
        pictures.add(new PictureVO(3, "http://www.idealista.com/pictures/4", "HD"));
        pictures.add(new PictureVO(4, "http://www.idealista.com/pictures/5", "SD"));
        pictures.add(new PictureVO(5, "http://www.idealista.com/pictures/6", "SD"));
        pictures.add(new PictureVO(6, "http://www.idealista.com/pictures/7", "SD"));
        pictures.add(new PictureVO(7, "http://www.idealista.com/pictures/8", "HD"));
    }

    public List<AdVO> getAllAds(){
        return this.ads;
    }

    public List<PictureVO> getPicturesById(List<Integer> pictureIds){
        List<PictureVO> picturesVO = new ArrayList<>(pictureIds.size());
        pictureIds.forEach(pictureId -> picturesVO.add(pictures.get(pictureId)));
        return picturesVO;
    }

    public void update(AdVO adVo){
        ads.set(adVo.getId(), adVo);
    }

    public void save(AdVO adVo){
        adVo.setId(ads.size());
        ads.add(adVo);
    }

    public Optional<AdVO> getAdById(Integer id){
        return Optional.ofNullable(ads.get(id));
    }
}
