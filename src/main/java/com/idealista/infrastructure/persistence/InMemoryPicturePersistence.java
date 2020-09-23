package com.idealista.infrastructure.persistence;

import com.idealista.domain.picture.Picture;
import com.idealista.domain.picture.PictureQuality;
import com.idealista.domain.picture.PictureRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryPicturePersistence implements PictureRepository {

    private final List<Picture> pictures;

    public InMemoryPicturePersistence() {
        pictures = new ArrayList<>();
        pictures.add(new Picture("bd962976-31fe-4442-8a83-b0c865034730", "http://www.idealista.com/pictures/bd962976-31fe-4442-8a83-b0c865034730", PictureQuality.SD));
        pictures.add(new Picture("a8bcb6c1-6183-49de-9f51-b4a6d9b55b41", "http://www.idealista.com/pictures/a8bcb6c1-6183-49de-9f51-b4a6d9b55b41", PictureQuality.HD));
        pictures.add(new Picture("7655de03-09dc-4624-93f8-2b19aa75e315", "http://www.idealista.com/pictures/7655de03-09dc-4624-93f8-2b19aa75e315", PictureQuality.SD));
        pictures.add(new Picture("5410b094-bd39-4f52-962f-8cc075514828", "http://www.idealista.com/pictures/5410b094-bd39-4f52-962f-8cc075514828", PictureQuality.HD));
        pictures.add(new Picture("1323483f-3070-498d-8116-305b535dead7", "http://www.idealista.com/pictures/1323483f-3070-498d-8116-305b535dead7", PictureQuality.SD));
        pictures.add(new Picture("ab214736-0613-4009-a247-c7a60debdb1f", "http://www.idealista.com/pictures/ab214736-0613-4009-a247-c7a60debdb1f", PictureQuality.SD));
        pictures.add(new Picture("75adac83-c2dd-46bf-9cd6-fc1c6331a56c", "http://www.idealista.com/pictures/75adac83-c2dd-46bf-9cd6-fc1c6331a56c", PictureQuality.SD));
        pictures.add(new Picture("5d8770bc-ace9-41c7-b391-da2b2c886884", "http://www.idealista.com/pictures/5d8770bc-ace9-41c7-b391-da2b2c886884", PictureQuality.HD));
    }


    @Override
    public List<Picture> getAllPictures() {
        return pictures;
    }

    @Override
    public List<Picture> findPictureByIds(List<String> ids) {
        return pictures.stream()
                .filter(picture -> ids.contains(picture.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findPictureUrlsByIds(List<String> ids) {
        return pictures.stream()
                .filter(picture -> ids.contains(picture.getId()))
                .map(Picture::getUrl)
                .collect(Collectors.toList());
    }

}












