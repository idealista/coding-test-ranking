package com.idealista.application.service.picture;

import com.idealista.domain.picture.Picture;

import java.util.List;

public interface PictureService {

    List<Picture> getPictures(List<String> ids);

    List<String> getPictureUrlsByIds(List<String> pictures);

}
