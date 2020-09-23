package com.idealista.application.service.score;

import com.idealista.application.service.score.rules.ScoreRuleService;
import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "score.min = 0",
        "score.max = 100",
        "score.min-relevant-score = 30",
})
public class ScoreServiceImplTest {

    @Value("${score.min}")
    private Integer minScore;
    @Value("${score.max}")
    private Integer maxScore;
    @Value("${score.min-relevant-score}")
    private Integer minRelevantScore;

    private final AdRepository repository = mock(AdRepository.class);

    private final ScoreRuleService scoreRuleServiceMock = mock(ScoreRuleService.class);

    private final Set<ScoreRuleService> scoreRules = Stream.of(scoreRuleServiceMock).collect(Collectors.toSet());

    private ScoreServiceImpl scoreService;

    @Before
    public void setUp() {
        scoreService = new ScoreServiceImpl(repository, scoreRules, minScore, maxScore, minRelevantScore);
    }

    @Test
    public void shouldCalculateAllAdsScore() {
        final Ad mockAdA = mock(Ad.class);
        final Ad mockAdB = mock(Ad.class);
        final Ad mockAdC = mock(Ad.class);
        final Ad mockAdD = mock(Ad.class);
        final List<Ad> adList = Arrays.asList(mockAdA, mockAdB, mockAdC, mockAdD);

        when(repository.getAllAds()).thenReturn(adList);

        assertThatCode(() -> scoreService.calculateAdsScore()).doesNotThrowAnyException();

        verify(repository).getAllAds();
        verify(mockAdA).updateScore(any(), any());
        verify(mockAdB).updateScore(any(), any());
        verify(mockAdC).updateScore(any(), any());
        verify(mockAdD).updateScore(any(), any());
        verify(scoreRuleServiceMock, times(4)).calculateScore(any());
        verify(repository, times(4)).saveAd(any());
        verifyNoMoreInteractions(mockAdA, mockAdB, mockAdC, mockAdD, scoreRuleServiceMock, repository);
    }

    @Test
    public void shouldNotCalculateWhenRepositoryReturnEmptyList() {
        final List<Ad> adList = Collections.emptyList();

        when(repository.getAllAds()).thenReturn(adList);

        assertThatCode(() -> scoreService.calculateAdsScore()).doesNotThrowAnyException();

        verify(repository).getAllAds();
        verify(scoreRuleServiceMock, never()).calculateScore(any());
        verify(repository,never()).saveAd(any());
        verifyNoMoreInteractions(scoreRuleServiceMock, repository);
    }

}
