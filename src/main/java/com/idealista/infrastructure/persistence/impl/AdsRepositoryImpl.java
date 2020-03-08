package com.idealista.infrastructure.persistence.impl;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;
import com.idealista.infrastructure.persistence.AdsRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AdsRepositoryImpl implements AdsRepository {

    protected Map<Integer, AdVO> domainMap;

    public AdsRepositoryImpl() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    @Override
    public List<AdVO> findAll() {
        return new ArrayList<>(domainMap.values());
    }

    @Override
    public AdVO findById(Integer id) { return domainMap.get(id); }

    @Override
    public AdVO saveOrUpdate(AdVO domainObject) {
        if (domainObject != null){

            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);

            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1;
    }

    private void loadDomainObjects() {
        domainMap = new HashMap<>();

        domainMap.put(1,new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Collections.<PictureVO>emptyList(), 300, null, null, null));
        domainMap.put(2, new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
                Arrays.asList(new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")),
                300, null, null, null));
        domainMap.put(3, new AdVO(3, "CHALET", "",
                Arrays.asList(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD")),
                300, null, null, null));
        domainMap.put(4, new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(new PictureVO(5, "http://www.idealista.com/pictures/5", "SD")),
                300, null, null, null));
        domainMap.put(5, new AdVO(5, "FLAT", "Pisazo,",
                Arrays.asList(
                        new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"),
                        new PictureVO(8, "http://www.idealista.com/pictures/8", "HD")),
                300, null, null, null));
        domainMap.put(6, new AdVO(6, "GARAGE", "",
                Arrays.asList(new PictureVO(6, "http://www.idealista.com/pictures/6", "SD")), 300, null, 10, null));
        domainMap.put(7, new AdVO(7, "GARAGE", "Garaje en el centro de Albacete",
                Collections.<PictureVO>emptyList(), 300, null, 80, null));
        domainMap.put(8, new AdVO(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!",
                Arrays.asList(
                        new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"),
                        new PictureVO(7, "http://www.idealista.com/pictures/7", "SD")),
                300, null, 90, null));

    }



}
