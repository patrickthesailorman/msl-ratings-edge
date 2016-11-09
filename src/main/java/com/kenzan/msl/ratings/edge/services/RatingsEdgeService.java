package com.kenzan.msl.ratings.edge.services;

/**
 * @author Kenzan
 */
public interface RatingsEdgeService {

  void rateContent(final String albumId, final Integer rating, final String sessionToken,
      final String contentType);

}
