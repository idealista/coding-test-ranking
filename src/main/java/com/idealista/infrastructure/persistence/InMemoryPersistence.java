package com.idealista.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

public interface InMemoryPersistence {

    List<AdVO> getAllAds();

    List<PictureVO> getPicturesById(List<Integer> pictureIds);

    void update(AdVO adVo);

    void save(AdVO adVo);

    Optional<AdVO> getAdById(Integer id);
}
