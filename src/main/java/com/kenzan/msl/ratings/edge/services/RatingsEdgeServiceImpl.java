package com.kenzan.msl.ratings.edge.services;

import com.google.inject.Inject;
import com.kenzan.msl.common.ContentType;
import com.kenzan.msl.ratings.client.dto.AverageRatingsDto;
import com.kenzan.msl.ratings.client.dto.UserRatingsDto;
import com.kenzan.msl.ratings.client.services.RatingsDataClientService;

import java.util.HashMap;
import java.util.UUID;

public class RatingsEdgeServiceImpl implements RatingsEdgeService {

  private final RatingsDataClientService ratingsDataClientService;

  public static HashMap archaiusProperties;

  @Inject
  public RatingsEdgeServiceImpl(final RatingsDataClientService ratingsDataClientService) {
    this.ratingsDataClientService = ratingsDataClientService;
  }

  /**
   * Rates user specific content and update the average ratings table
   * 
   * @param albumId String
   * @param rating Integer
   * @param sessionToken String
   * @param contentType Album|Song|Artist
   */
  public void rateContent(final String albumId, final Integer rating, final String sessionToken,
      final String contentType) {

    if (ContentType.hasValidContentType(contentType)) {
      // STEP 1: add or update user rating.
      UserRatingsDto userRatingsDto = new UserRatingsDto();
      userRatingsDto.setUserId(UUID.fromString(sessionToken));
      userRatingsDto.setContentUuid(UUID.fromString(albumId));
      userRatingsDto.setRating(rating);
      userRatingsDto.setContentType(contentType);

      // STEP 2: update average ratings
      AverageRatingsDto averageRatingsDto = new AverageRatingsDto();
      averageRatingsDto.setContentId(UUID.fromString(albumId));
      averageRatingsDto.setSumRating(new Long(1));
      averageRatingsDto.setNumRating(new Long(rating));
      averageRatingsDto.setContentType(contentType);

      ratingsDataClientService.addOrUpdateUserRatings(userRatingsDto);
      ratingsDataClientService.addOrUpdateAverageRating(averageRatingsDto);
    } else {
      throw new RuntimeException("Invalid content type");
    }
  }

}
