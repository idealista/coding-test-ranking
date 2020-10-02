package com.idealista.domain.mapper;


import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.domain.Generator;
import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdMapperTest {
	
	@Autowired
	private AdMapper mapper;
	
	@MockBean
	private AdRepository repository;

	@Test
	public void whenReceivesAdVO_souldReturnPublicAdObject() throws Exception {
		AdVO ad = Generator.generateNullAdVO();
		
		assertEquals(PublicAd.class, mapper.adVOToPublicAd(ad).getClass());
	}
	
	@Test
	public void whenReceivesAdVO_shouldReturnPublicAdWithCorrectFields() {
		
		AdVO ad = new AdVO(1, "FLAT", "New loft", List.of(1,2), 1, 1, 1, new Date());
		
		PictureVO pic1 = new PictureVO(1, "url1.com", "SD");
		PictureVO pic2 = new PictureVO(2, "url2.com", "SD");
		
		when(repository.findPictureById(1)).thenReturn(pic1);
		when(repository.findPictureById(2)).thenReturn(pic2);
		
		assertEquals("Id check: ", ad.getId(), mapper.adVOToPublicAd(ad).getId());
		assertEquals("Typology check: ", 
				ad.getTypology(), mapper.adVOToPublicAd(ad).getTypology());
		assertEquals("Description check: ", 
				ad.getDescription(), mapper.adVOToPublicAd(ad).getDescription());
		assertEquals("Pictures check: ", 
				List.of(pic1.getUrl(), pic2.getUrl()), mapper.adVOToPublicAd(ad).getPictureUrls());
		assertEquals("HouseSize check: ", 
				ad.getHouseSize(), mapper.adVOToPublicAd(ad).getHouseSize());
		assertEquals("GardenSize check: ", 
				ad.getGardenSize(), mapper.adVOToPublicAd(ad).getGardenSize());
		
	}
	
	@Test
	public void whenReceivesAdVO_souldReturnQualityAdObject() throws Exception {
		AdVO ad = Generator.generateNullAdVO();
		
		assertEquals(QualityAd.class, mapper.adVOToQualityAd(ad).getClass());
	}
	
	@Test
	public void whenReceivesAdVO_shouldReturnQualityAdWithCorrectFields() {
		
		AdVO ad = new AdVO(1, "FLAT", "New loft", List.of(1,2), 1, 1, 1, new Date());
		
		PictureVO pic1 = new PictureVO(1, "url1.com", "SD");
		PictureVO pic2 = new PictureVO(2, "url2.com", "SD");
		
		when(repository.findPictureById(1)).thenReturn(pic1);
		when(repository.findPictureById(2)).thenReturn(pic2);
		
		assertEquals("Id check: ", ad.getId(), mapper.adVOToQualityAd(ad).getId());
		assertEquals("Typology check: ", 
				ad.getTypology(), mapper.adVOToQualityAd(ad).getTypology());
		assertEquals("Description check: ", 
				ad.getDescription(), mapper.adVOToQualityAd(ad).getDescription());
		assertEquals("Pictures check: ", 
				List.of(pic1.getUrl(), pic2.getUrl()), mapper.adVOToQualityAd(ad).getPictureUrls());
		assertEquals("HouseSize check: ", 
				ad.getHouseSize(), mapper.adVOToQualityAd(ad).getHouseSize());
		assertEquals("GardenSize check: ", 
				ad.getGardenSize(), mapper.adVOToQualityAd(ad).getGardenSize());
		assertEquals("Score check: ", 
				ad.getScore(), mapper.adVOToQualityAd(ad).getScore());
		assertEquals("Date check: ", 
				ad.getIrrelevantSince(), mapper.adVOToQualityAd(ad).getIrrelevantSince());
		
	}

}
