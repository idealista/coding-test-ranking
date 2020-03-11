package com.idealista.infrastructure.controllers;

import com.idealista.infrastructure.exceptions.ScoringIncompleteException;
import com.idealista.infrastructure.services.ads.listing.GetPublicAdsService;
import com.idealista.infrastructure.services.ads.listing.GetQualityAdsService;
import com.idealista.infrastructure.services.ads.scoring.ScoreAdsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections.ListUtils.EMPTY_LIST;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class AdsControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AdsController controller;

    @Mock
    private GetPublicAdsService getPublicAdsService;

    @Mock
    private GetQualityAdsService getQualityAdsService;

    @Mock
    private ScoreAdsService scoreAdsService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void shouldReturnNoContentWhenNoQualityAds() throws Exception {
        //given
        when(getQualityAdsService.get()).thenReturn(EMPTY_LIST);

        //when
        final ResultActions result = mockMvc.perform(
                get("/admin/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isNoContent());
        verify(getQualityAdsService, times(1)).get();
    }

    @Test
    public void shouldReturnContentWhenExistsQualityAds() throws Exception {
        //given
        List<QualityAd> ads = Collections.singletonList(new QualityAd());
        when(getQualityAdsService.get()).thenReturn(ads);

        //when
        final ResultActions result = mockMvc.perform(
                get("/admin/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isOk());
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(getQualityAdsService, times(1)).get();
    }

    @Test
    public void shouldReturnNoContentWhenNoPublicAds() throws Exception {
        //given
        when(getPublicAdsService.get()).thenReturn(EMPTY_LIST);

        //when
        final ResultActions result = mockMvc.perform(
                get("/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isNoContent());
        verify(getPublicAdsService, times(1)).get();
    }

    @Test
    public void shouldReturnContentWhenExistsPublicAds() throws Exception {
        //given
        List<PublicAd> ads = Collections.singletonList(new PublicAd());
        when(getPublicAdsService.get()).thenReturn(ads);

        //when
        final ResultActions result = mockMvc.perform(
                get("/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isOk());
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(getPublicAdsService, times(1)).get();
    }

    @Test
    public void shouldReturnNoContentWhenScoreAds() throws Exception {
        //given
        doNothing().when(scoreAdsService).score();

        //when
        final ResultActions result = mockMvc.perform(
                post("/ads/score")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isNoContent());
        verify(scoreAdsService, times(1)).score();
    }

    @Test
    public void shouldReturnInternalServerErrorWhenIncompleteScoring() throws Exception {
        //given
        doThrow(new ScoringIncompleteException(EMPTY, new Exception())).when(scoreAdsService).score();

        //when
        final ResultActions result = mockMvc.perform(
                post("/ads/score")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        result.andExpect(status().isInternalServerError());
        verify(scoreAdsService, times(1)).score();
    }

}