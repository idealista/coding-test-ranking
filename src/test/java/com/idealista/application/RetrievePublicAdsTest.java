package com.idealista.application;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.idealista.domain.Ad;
import com.idealista.domain.AdIdentifer;
import com.idealista.domain.AdsCollection;
import com.idealista.domain.Picture;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RetrievePublicAdsTest {

  private final AdsCollection adsCollection = mock(AdsCollection.class);
  private final RetrievePublicAds retrievePublicAds = new RetrievePublicAds(adsCollection);

  @Test
  void should_retrieve_all_the_valid_ads_to_users() {
    //given
    given(adsCollection.getAllAdsWithScore()).willReturn(asList(
        new Ad(new AdIdentifer(1), "FLAT", "Description for ad with id 1", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 40, null),
        new Ad(new AdIdentifer(2), "FLAT", "Description for ad with id 2", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 39, null),
        new Ad(new AdIdentifer(3), "FLAT", "Description for ad with id 3", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, 70, null)
    ));

    //when
    List<Ad> retrievedAds = retrievePublicAds.execute();

    //then
    assertFalse(retrievedAds.isEmpty());
    assertEquals(2, retrievedAds.size());
  }
}
