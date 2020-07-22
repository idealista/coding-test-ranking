package com.idealista.application.repository;

import java.util.List;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

public interface AdRepository {
  //TODO crea los métodos que necesites
  List<AdVO> findAdVO();

  List<PictureVO> findPictureVO();
}
