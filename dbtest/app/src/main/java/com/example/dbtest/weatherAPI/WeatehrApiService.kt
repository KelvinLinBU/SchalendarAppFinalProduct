package com.example.dbtest.weatherAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q") cityName: String, // QUERY for GET request
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}