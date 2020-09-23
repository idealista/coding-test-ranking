package com.idealista.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void order1_shouldListPublicAdsReturnEmptyList() throws Exception {
        mockMvc.perform(get("/ads/")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    public void order2_shouldListQualityAdsReturnAllWithNullScores() throws Exception {
        mockMvc.perform(get("/ads/quality")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(8)))
                .andExpect(jsonPath("$[0].score").value(nullValue()))
                .andExpect(jsonPath("$[0].irrelevantSince").value(nullValue()));
    }

    @Test
    public void order3_shouldCalculateScoreOfAllAds() throws Exception {
        mockMvc.perform(post("/ads/score")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(204))
                .andExpect(content().string(""));
    }

    @Test
    public void order4_shouldListPublicAdsAfterCalculateScore() throws Exception {
        mockMvc.perform(get("/ads/")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(hasSize(4)))
                .andExpect(jsonPath("$[0].*").value(hasSize(6)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].typology").isString())
                .andExpect(jsonPath("$[0].description").isString())
                .andExpect(jsonPath("$[0].pictureUrls").isArray())
                .andExpect(jsonPath("$[0].houseSize").value(anyOf(nullValue(), instanceOf(Number.class))))
                .andExpect(jsonPath("$[0].gardenSize").value(anyOf(nullValue(), instanceOf(Number.class))));
    }

    @Test
    public void order5_shouldListQualityAdsAfterCalculateScore() throws Exception {
        mockMvc.perform(get("/ads/quality")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(hasSize(4)))
                .andExpect(jsonPath("$[0].*").value(hasSize(8)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].typology").isString())
                .andExpect(jsonPath("$[0].description").isString())
                .andExpect(jsonPath("$[0].pictureUrls").isArray())
                .andExpect(jsonPath("$[0].houseSize").value(anyOf(nullValue(), instanceOf(Number.class))))
                .andExpect(jsonPath("$[0].gardenSize").value(anyOf(nullValue(), instanceOf(Number.class))))
                .andExpect(jsonPath("$[0].score").value(anyOf(nullValue(), instanceOf(Number.class))))
                .andExpect(jsonPath("$[0].irrelevantSince").value(anyOf(nullValue(), isDate())));
    }

    private static Matcher<Object> isDate() {
        return new CustomMatcher<Object>("not match java.util.Date") {
            @Override
            public boolean matches(Object o) {
                if (Objects.isNull(o)) return false;

                try {
                    new ObjectMapper().convertValue(o, Date.class);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }
}

