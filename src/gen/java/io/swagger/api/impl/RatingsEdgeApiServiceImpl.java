package io.swagger.api.impl;

import com.google.inject.Inject;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeServiceImpl;
import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import io.swagger.model.ErrorResponse;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2016-01-25T12:48:18.840-06:00")
public class RatingsEdgeApiServiceImpl extends RatingsEdgeApiService {


    private final RatingsEdgeServiceImpl ratingsEdgeService;
    private RatingsEdgeSessionToken ratingsEdgeSessionToken;

    @Inject
    public RatingsEdgeApiServiceImpl (final RatingsEdgeServiceImpl ratingsEdgeService,
                                      final RatingsEdgeSessionToken ratingsEdgeSessionToken) {
        this.ratingsEdgeService = ratingsEdgeService;
        this.ratingsEdgeSessionToken = ratingsEdgeSessionToken;
    }

    @Override
    public Response rateAlbum(String albumId, Integer rating)
            throws NotFoundException {
        if (ratingsEdgeSessionToken.isValidToken()) {
            try {
                ratingsEdgeService.rateContent(albumId, rating, ratingsEdgeSessionToken.getTokenValue(), "Album");
                return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "sucess")).build();
            } catch (Exception e) {
                e.printStackTrace();

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Server error: " + e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new RatingsEdgeApiResponseMessage(RatingsEdgeApiResponseMessage.ERROR, "no valid sessionToken provided")).build();
        }
    }

    @Override
    public Response rateArtist(String artistId, Integer rating)
            throws NotFoundException {
        if (ratingsEdgeSessionToken.isValidToken()) {
            try {
                ratingsEdgeService.rateContent(artistId, rating, ratingsEdgeSessionToken.getTokenValue(), "Artist");
                return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "sucess")).build();
            } catch (Exception e) {
                e.printStackTrace();

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Server error: " + e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new RatingsEdgeApiResponseMessage(RatingsEdgeApiResponseMessage.ERROR, "no valid sessionToken provided")).build();
        }
    }

    @Override
    public Response rateSong(String songId, Integer rating)
            throws NotFoundException {
        if (ratingsEdgeSessionToken.isValidToken()) {
            try {
                ratingsEdgeService.rateContent(songId, rating, ratingsEdgeSessionToken.getTokenValue(), "Song");
                return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "sucess")).build();
            } catch (Exception e) {
                e.printStackTrace();

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("Server error: " + e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new RatingsEdgeApiResponseMessage(RatingsEdgeApiResponseMessage.ERROR, "no valid sessionToken provided")).build();
        }
    }

}
