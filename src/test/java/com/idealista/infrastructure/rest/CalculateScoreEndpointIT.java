package com.idealista.infrastructure.rest;

import com.idealista.application.CalculateAdsScore;
import com.idealista.infrastructure.api.inbound.CalculateScoreController;
import com.idealista.infrastructure.rest.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CalculateScoreController.class)
@ContextConfiguration(classes = {Config.class})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles({"test", "it", "rest"})
public class CalculateScoreEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculateAdsScore calculateAdsScore;

    @Test
    @DisplayName("When the REST endpoint is called Then the score should be calculate for each stored ad")
    void shouldCalculateTheScoreForTheStoredAds() throws Exception {
        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/1/ad/calculate-score"));

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
