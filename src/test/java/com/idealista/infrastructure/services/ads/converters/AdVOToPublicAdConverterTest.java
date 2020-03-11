package com.idealista.infrastructure.services.ads.converters;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdVOToPublicAdConverterTest {

    private AdVOToPublicAdConverter converter = new AdVOToPublicAdConverter();

    @Before
    public void setUp() {
        converter = new AdVOToPublicAdConverter();
    }

    @Test
    public void shouldReturnNullWhenAdVOisNull() {
        //when
        PublicAd out = converter.convert(null);

        //then
        assertThat(out, is(nullValue()));
    }

    @Test
    public void shouldReturnPublicAdWhenAdVOisValid() {
        //given
        AdVO in = MockObjectFactory.getAdVOChalet65();
        //when
        PublicAd out = converter.convert(in);

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
    }
}