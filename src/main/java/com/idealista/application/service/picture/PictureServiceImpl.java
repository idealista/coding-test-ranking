package com.idealista.application.service.picture;

import com.idealista.domain.picture.Picture;
import com.idealista.domain.picture.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository repository;

    public PictureServiceImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Picture> getPictures(List<String> ids) {
        return repository.findPictureByIds(ids);
    }

    @Override
    public List<String> getPictureUrlsByIds(List<String> ids) {
        return repository.findPictureUrlsByIds(ids);
    }

}
