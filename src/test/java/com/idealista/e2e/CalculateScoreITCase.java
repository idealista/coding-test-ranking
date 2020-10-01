package com.idealista.e2e;

import com.idealista.Main;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CalculateScoreITCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryPersistence inMemoryPersistence;

    @Test
    void shouldCalculateTheScoreForStoredAds() throws Exception {
        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/1/ad/calculate-score"));

        //then
        resultActions.andExpect(status().isOk());
        final List<AdVO> allAds = inMemoryPersistence.findAllAds();
        assertThat(allAds).hasSize(8).extracting("score").isNotNull();
    }
}
