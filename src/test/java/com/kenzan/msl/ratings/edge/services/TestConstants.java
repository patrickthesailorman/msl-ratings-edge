package com.kenzan.msl.ratings.edge.services;

import com.kenzan.msl.ratings.client.dao.AverageRatingsDao;
import com.kenzan.msl.ratings.client.dao.UserRatingsDao;

import java.util.UUID;

public class TestConstants {

    public final UUID ALBUM_ID = UUID.fromString("00000000-0000-0000-0000-000000001");
    public final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000001");

    public final String ALBUM_CONTENT_TYPE = "Album";
    public final Integer RATING = 4;

    public AverageRatingsDao averageRatingsDao = new AverageRatingsDao();
    public UserRatingsDao userRatingsDao = new UserRatingsDao();

    private static TestConstants instance = null;

    private TestConstants() {
        averageRatingsDao.setNumRating(new Long(3));
        averageRatingsDao.setSumRating(new Long(1));
        averageRatingsDao.setContentType(ALBUM_CONTENT_TYPE);
        averageRatingsDao.setContentId(ALBUM_ID);

        userRatingsDao.setContentUuid(ALBUM_ID);
        userRatingsDao.setUserId(USER_ID);
        userRatingsDao.setContentType(ALBUM_CONTENT_TYPE);
        userRatingsDao.setRating(3);
    }

    public static TestConstants getInstance() {
        if ( instance == null ) {
            instance = new TestConstants();
        }
        return instance;
    }
}
