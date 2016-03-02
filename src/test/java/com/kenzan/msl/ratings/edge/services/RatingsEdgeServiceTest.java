package com.kenzan.msl.ratings.edge.services;

import com.kenzan.msl.ratings.client.services.CassandraRatingsService;
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
@PrepareForTest({CassandraRatingsService.class})
public class RatingsEdgeServiceTest {

  private TestConstants tc = TestConstants.getInstance();
  private CassandraRatingsService cassandraRatingsService;

  @Before
  public void init() throws Exception {
    PowerMock.mockStatic(CassandraRatingsService.class);
    cassandraRatingsService = createMock(CassandraRatingsService.class);
    PowerMock.expectNew(CassandraRatingsService.class).andReturn(cassandraRatingsService);
    expect(CassandraRatingsService.getInstance()).andReturn(cassandraRatingsService).anyTimes();
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

    RatingsEdgeService ratingsEdgeService = new RatingsEdgeService();
    ratingsEdgeService.rateContent(tc.ALBUM_ID.toString(), tc.RATING, tc.USER_ID.toString(),
        tc.ALBUM_CONTENT_TYPE);
  }

  @Test(expected = RuntimeException.class)
  public void testInvalidContentType() {
    EasyMock.replay(cassandraRatingsService);
    PowerMock.replayAll();

    /* ********************************** */
    RatingsEdgeService ratingsEdgeService = new RatingsEdgeService();
    ratingsEdgeService.rateContent(tc.ALBUM_ID.toString(), tc.RATING, tc.USER_ID.toString(),
        "INVALID_CONTENT_TYPE");
  }
}
