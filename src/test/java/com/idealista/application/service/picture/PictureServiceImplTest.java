package com.idealista.application.service.picture;

import com.idealista.domain.picture.Picture;
import com.idealista.domain.picture.PictureCreator;
import com.idealista.domain.picture.PictureRepository;
import com.idealista.util.BaseCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PictureServiceImplTest {

    @Mock
    private PictureRepository repository;

    @InjectMocks
    private PictureServiceImpl pictureServiceImpl;

    @Test
    public void shouldReturnPicturesFromRepository() {
        final List<Picture> expected = BaseCreator.randomList(BaseCreator.randomNumber(), PictureCreator::create);

        when(repository.findPictureByIds(anyList())).thenReturn(expected);

        assertThat(pictureServiceImpl.getPictures(anyList())).isEqualTo(expected);

        verify(repository, only()).findPictureByIds(anyList());
    }

    @Test
    public void shouldReturnPicturesUrlsFromRepository() {
        final List<String> expected = BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomSentence);

        when(repository.findPictureUrlsByIds(anyList())).thenReturn(expected);

        assertThat(pictureServiceImpl.getPictureUrlsByIds(anyList())).isEqualTo(expected);

        verify(repository, only()).findPictureUrlsByIds(anyList());
    }

}
