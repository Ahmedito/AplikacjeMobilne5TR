package com.example.nasaphotoapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApiService {

    @GET("planetary/apod")
    Call<NasaPhoto> getTodayPhoto(@Query("api_key") String apiKey);

    @GET("planetary/apod")
    Call<NasaPhoto> getPhotoByDate(@Query("api_key") String apiKey, @Query("date") String date);
}
