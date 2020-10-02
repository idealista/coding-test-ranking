package com.idealista.infrastructure.api;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.idealista.domain.service.PublicAdService;
import com.idealista.domain.service.QualityAdService;
import com.idealista.domain.service.ScoreAdService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdsControllerTest {
	
	private static final int EMPTY_LIST_SIZE = 0;

	@Autowired
	private MockMvc mock;
	
	@MockBean
	private PublicAdService publicAdService;
	
	@MockBean
	private QualityAdService qualityAdService;
	
	@MockBean
	private ScoreAdService scoreAdService;
	
	@Test
	public void whenPublicListingAndEmptyPublicAds_shouldReturnNoAds() throws Exception{
		List<PublicAd> publicAds = Collections.emptyList();
		
		when(publicAdService.getAds()).thenReturn(publicAds);
		
		mock.perform(get("/ads")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").value(hasSize(EMPTY_LIST_SIZE)));
		
	}
	
	@Test
	public void whenPublicListing_shouldListAllThePublicAds() throws Exception{
		List<PublicAd> publicAds = 
				List.of(new PublicAd[]{new PublicAd(), new PublicAd()});
		
		when(publicAdService.getAds()).thenReturn(publicAds);
		
		mock.perform(get("/ads")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").value(hasSize(2)));
	}
	
	@Test
	public void whenQualityListingAndEmptyQualityAds_shouldReturnNoAds() throws Exception{
		List<QualityAd> qualityAds = Collections.emptyList();
		
		when(qualityAdService.getAds()).thenReturn(qualityAds);
		
		mock.perform(get("/ads/quality")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").value(hasSize(EMPTY_LIST_SIZE)));
		
	}
	
	@Test
	public void whenQualityListing_shouldListAllTheQualityAds() throws Exception{
		List<QualityAd> qualityAds = 
				List.of(new QualityAd[]{new QualityAd(), new QualityAd()});
		
		when(qualityAdService.getAds()).thenReturn(qualityAds);
		
		mock.perform(get("/ads/quality")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").value(hasSize(2)));
	}
	
	@Test
	public void whenCalculateScore_shouldRespondWithNoContentMessage() throws Exception {
		mock.perform(post("/ads/score")).andExpect(status().isNoContent());
	}
	
	@Test
	public void whenCalculateScore_shouldCallCalculateScoreMethodFromScoreAdService() throws Exception {
		mock.perform(post("/ads/score"));
		
		verify(scoreAdService, times(1)).calculateScore();
	}

}
