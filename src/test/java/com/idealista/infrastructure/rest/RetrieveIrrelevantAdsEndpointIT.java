package com.idealista.infrastructure.rest;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.idealista.application.RetrieveIrrelevantAds;
import com.idealista.domain.Ad;
import com.idealista.domain.AdIdentifer;
import com.idealista.domain.Picture;
import com.idealista.infrastructure.api.inbound.RetrieveIrrelevantAdsController;
import com.idealista.infrastructure.rest.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RetrieveIrrelevantAdsController.class)
@ContextConfiguration(classes = {Config.class})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles({"test", "it", "rest"})
public class RetrieveIrrelevantAdsEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrieveIrrelevantAds retrieveIrrelevantAds;

    @Test
    @DisplayName("When the REST endpoint is called Then the score should be calculate for each stored ad")
    void shouldCalculateTheScoreForTheStoredAds() throws Exception {
        //given
        BDDMockito.given(retrieveIrrelevantAds.execute()).willReturn(asList(
            new Ad(new AdIdentifer(1), "FLAT", "Description for ad with id 1", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 40, null),
            new Ad(new AdIdentifer(2), "FLAT", "Description for ad with id 2", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 39, null),
            new Ad(new AdIdentifer(3), "FLAT", "Description for ad with id 3", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 5, null)
        ));

        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/1/ad/irrelevant-ads"));

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0]", is(notNullValue())))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$.[0].id", is(1)))
            .andExpect(jsonPath("$.[1]", is(notNullValue())))
            .andExpect(jsonPath("$.[1].id", is(2)))
            .andExpect(jsonPath("$.[2]", is(notNullValue())))
            .andExpect(jsonPath("$.[2].id", is(3)));
    }
}
