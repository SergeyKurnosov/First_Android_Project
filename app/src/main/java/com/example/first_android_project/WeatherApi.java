package com.example.first_android_project;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<JsonObject> getWeather(@Query("q")String city,
                                @Query("appid")String apikey,
                                @Query("units")String units,
                                @Query("lang")String lang);
}
