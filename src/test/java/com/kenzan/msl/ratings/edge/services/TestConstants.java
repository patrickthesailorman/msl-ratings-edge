package com.kenzan.msl.ratings.edge.services;

import com.kenzan.msl.ratings.client.dto.AverageRatingsDto;
import com.kenzan.msl.ratings.client.dto.UserRatingsDto;

import java.util.UUID;

public class TestConstants {

    public final UUID ALBUM_ID = UUID.fromString("00000000-0000-0000-0000-000000001");
    public final UUID USER_ID = UUID.fromString("00000000-0000-0000-0000-000000001");

    public final String ALBUM_CONTENT_TYPE = "Album";
    public final Integer RATING = 4;

    public AverageRatingsDto averageRatingsDto = new AverageRatingsDto();
    public UserRatingsDto userRatingsDto = new UserRatingsDto();

    private static TestConstants instance = null;

    private TestConstants() {
        averageRatingsDto.setNumRating(new Long(3));
        averageRatingsDto.setSumRating(new Long(1));
        averageRatingsDto.setContentType(ALBUM_CONTENT_TYPE);
        averageRatingsDto.setContentId(ALBUM_ID);

        userRatingsDto.setContentUuid(ALBUM_ID);
        userRatingsDto.setUserId(USER_ID);
        userRatingsDto.setContentType(ALBUM_CONTENT_TYPE);
        userRatingsDto.setRating(3);
    }

    public static TestConstants getInstance() {
        if ( instance == null ) {
            instance = new TestConstants();
        }
        return instance;
    }
}
