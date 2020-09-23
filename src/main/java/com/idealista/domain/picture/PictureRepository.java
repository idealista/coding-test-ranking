package com.idealista.domain.picture;

import java.util.List;

public interface PictureRepository {

    List<Picture> getAllPictures();

    List<Picture> findPictureByIds(List<String> ids);

    List<String> findPictureUrlsByIds(List<String> ids);

}
