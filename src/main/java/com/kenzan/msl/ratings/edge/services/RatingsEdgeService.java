package com.kenzan.msl.ratings.edge.services;

import com.kenzan.msl.ratings.client.dao.AverageRatingsDao;
import com.kenzan.msl.ratings.client.dao.UserRatingsDao;
import com.kenzan.msl.ratings.client.services.CassandraRatingsService;

import java.util.UUID;

public class RatingsEdgeService {

    private CassandraRatingsService cassandraRatingsService;

    public RatingsEdgeService() {
        cassandraRatingsService = CassandraRatingsService.getInstance();
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

        if ( contentType.equals("Album") || contentType.equals("Artist") || contentType.equals("Song ") ) {
            // STEP 1: add or update user rating.
            UserRatingsDao userRatingsDao = new UserRatingsDao();
            userRatingsDao.setUserId(UUID.fromString(sessionToken));
            userRatingsDao.setContentUuid(UUID.fromString(albumId));
            userRatingsDao.setRating(rating);
            userRatingsDao.setContentType(contentType);

            // STEP 2: update average ratings
            AverageRatingsDao averageRatingsDao = new AverageRatingsDao();
            averageRatingsDao.setContentId(UUID.fromString(albumId));
            averageRatingsDao.setSumRating(new Long(1));
            averageRatingsDao.setNumRating(new Long(rating));
            averageRatingsDao.setContentType(contentType);

            cassandraRatingsService.addOrUpdateUserRatings(userRatingsDao);
            cassandraRatingsService.addOrUpdateAverageRating(averageRatingsDao);
        }
        else {
            throw new RuntimeException("Invalid content type");
        }
    }

}
