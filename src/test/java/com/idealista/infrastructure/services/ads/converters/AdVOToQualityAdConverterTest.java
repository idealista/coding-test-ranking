package com.idealista.infrastructure.services.ads.converters;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.controllers.QualityAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AdVOToQualityAdConverterTest {

    private AdVOToQualityAdConverter converter = new AdVOToQualityAdConverter();

    @Before
    public void setUp() {
        converter = new AdVOToQualityAdConverter();
    }

    @Test
    public void shouldReturnNullWhenAdVOisNull() {
        //when
        QualityAd out = converter.convert(null);

        //then
        assertThat(out, is(nullValue()));
    }

    @Test
    public void shouldReturnQualityAdWhenAdVOisValid() {
        //given
        AdVO in = MockObjectFactory.getAdVOChalet65();
        //when
        QualityAd out = converter.convert(in);

        //then
        assertThat(out, is(notNullValue()));
        assertEquals(out.getId(), in.getId());
        assertEquals(out.getTypology(), in.getTypology());
        assertEquals(out.getDescription(), in.getDescription());
        assertEquals(out.getHouseSize(), in.getHouseSize());
        assertEquals(out.getGardenSize(), in.getGardenSize());
        String[] pictures = in.getPictures().stream()
                .map(PictureVO::getUrl).collect(Collectors.toList()).toArray(new String [in.getPictures().size()]);
        assertThat(out.getPictureUrls(), containsInAnyOrder(pictures));
        assertEquals(out.getScore(), in.getScore());
        assertEquals(out.getIrrelevantSince(), in.getIrrelevantSince());

    }


}