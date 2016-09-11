package com.robsterthelobster.robinbmt;

import com.robsterthelobster.robinbmt.models.FoursquareCall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by robin on 9/10/2016.
 */
public interface FoursquareApi {

    @GET("venues/explore")
    Call<FoursquareCall> getVenues(@Query("client_id") String client_id,
                                   @Query("client_secret") String client_secret,
                                   @Query("ll") String location,
                                   @Query("query") String query,
                                   @Query("venuePhotos") int wantPhotos,
                                   @Query("v") String versionDate);
}
