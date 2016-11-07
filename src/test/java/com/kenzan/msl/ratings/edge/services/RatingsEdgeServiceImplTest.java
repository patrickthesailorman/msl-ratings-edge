package com.kenzan.msl.ratings.edge.services;

import com.kenzan.msl.ratings.client.services.RatingsDataClientService;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RatingsDataClientService.class})
public class RatingsEdgeServiceImplTest {

  private TestConstants tc = TestConstants.getInstance();
  private RatingsDataClientService cassandraRatingsService;

  @Before
  public void init() throws Exception {
    PowerMock.mockStatic(RatingsDataClientService.class);
    cassandraRatingsService = createMock(RatingsDataClientService.class);
  }

  @Test
  public void testRateContent() {
    EasyMock.expect(cassandraRatingsService.addOrUpdateUserRatings(EasyMock.anyObject()))
        .andReturn(Observable.empty());
    EasyMock.expect(cassandraRatingsService.addOrUpdateAverageRating(EasyMock.anyObject()))
        .andReturn(Observable.empty());

    EasyMock.replay(cassandraRatingsService);
    PowerMock.replayAll();

    /* ********************************** */

    RatingsEdgeServiceImpl ratingsEdgeService = new RatingsEdgeServiceImpl(cassandraRatingsService);
    ratingsEdgeService.rateContent(tc.ALBUM_ID.toString(), tc.RATING, tc.USER_ID.toString(),
        tc.ALBUM_CONTENT_TYPE);
  }

  @Test(expected = RuntimeException.class)
  public void testInvalidContentType() {
    EasyMock.replay(cassandraRatingsService);
    PowerMock.replayAll();

    /* ********************************** */
    RatingsEdgeServiceImpl ratingsEdgeService = new RatingsEdgeServiceImpl(cassandraRatingsService);
    ratingsEdgeService.rateContent(tc.ALBUM_ID.toString(), tc.RATING, tc.USER_ID.toString(),
        "INVALID_CONTENT_TYPE");
  }
}
