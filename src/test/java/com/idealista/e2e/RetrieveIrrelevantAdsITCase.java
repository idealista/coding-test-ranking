package com.idealista.e2e;

import com.idealista.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class RetrieveIrrelevantAdsITCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCalculateTheScoreForStoredAds() throws Exception {
        //given
        mockMvc.perform(put("/api/1/ad/calculate-score"));

        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/1/ad/irrelevant-ads"));

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0]", is(notNullValue())))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$.[0].id", is(1)))
            .andExpect(jsonPath("$.[1]", is(notNullValue())))
            .andExpect(jsonPath("$.[1].id", is(3)))
            .andExpect(jsonPath("$.[2]", is(notNullValue())))
            .andExpect(jsonPath("$.[2].id", is(7)));
    }
}
