package com.idealista.infrastructure.persistence;

import com.idealista.domain.picture.Picture;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryPicturePersistenceTest {

    private static InMemoryPicturePersistence inMemoryPicturePersistence;
    private static List<Picture> allPictures;

    @BeforeClass
    public static void beforeClass() {
        inMemoryPicturePersistence = new InMemoryPicturePersistence();

        allPictures = inMemoryPicturePersistence.getAllPictures();
    }

    @Test
    public void shouldGetAllAds() {
        assertThat(inMemoryPicturePersistence.getAllPictures()).isNotEmpty();
        assertThat(inMemoryPicturePersistence.getAllPictures()).hasOnlyElementsOfType(Picture.class);
    }

    @Test
    public void shouldFindPictureByIds() {
        final List<String> pictureIds = allPictures.stream()
                .map(Picture::getId)
                .collect(Collectors.toList());

        assertThat(inMemoryPicturePersistence.findPictureByIds(pictureIds)).containsAll(allPictures);
    }

    @Test
    public void shouldFindPictureUrlsByIds() {
        final List<String> pictureIds = allPictures.stream()
                .map(Picture::getId)
                .collect(Collectors.toList());

        final List<String> pictureUrls = allPictures.stream()
                .map(Picture::getUrl)
                .collect(Collectors.toList());

        assertThat(inMemoryPicturePersistence.findPictureUrlsByIds(pictureIds)).containsAll(pictureUrls);
    }
}
