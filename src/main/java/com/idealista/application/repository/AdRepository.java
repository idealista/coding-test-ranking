package com.idealista.application.repository;

import java.util.List;
import java.util.Optional;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public interface AdRepository {

  List<AdVO> findAdVO();

  Optional<PictureVO> findPictureVOById(Integer id);
}
