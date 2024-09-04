package com.example.synmediawether.repository

import com.example.synmediawether.pojo.WetherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/weather")
   suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): WetherResponse
}