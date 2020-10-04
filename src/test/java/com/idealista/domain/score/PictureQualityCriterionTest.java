package com.idealista.domain.score;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.domain.Generator;
import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.PictureVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureQualityCriterionTest {

	@Autowired
	@Qualifier("pictureQualityCriterion")
	private ScoreCriterion criterion;
	
	@MockBean
	private AdRepository repository;

	
	@Test
	public void whenAdContainsASDPicture_shouldSetPartialScoreTo10() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setPictures(List.of(1));
		
		when(repository.findPictureById(1)).thenReturn(new PictureVO(1, "", "SD"));
		
		assertEquals(10, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenAdContainsAHDPicture_shouldSetPartialScoreTo20() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setPictures(List.of(1));
		
		when(repository.findPictureById(1)).thenReturn(new PictureVO(1, "", "HD"));
		
		assertEquals(20, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenAdContainsBothHDAndSDPictures_shouldSetPartialScoreTo30() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setPictures(List.of(1,2));
		
		when(repository.findPictureById(1)).thenReturn(new PictureVO(1, "", "HD"));
		when(repository.findPictureById(2)).thenReturn(new PictureVO(2, "", "SD"));
		
		assertEquals(30, (int) criterion.getPartialScore(ad));
	}
}
