package com.example.dbtest.weatherAPI


// data classes for extracting response RETROFIT
data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
)

data class Wind(
    val speed: Double // Wind speed in meters per second
    // You can add other wind-related properties if needed
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    // other properties...
)

data class Main(
    val temp: Double,
    val humidity: Int,
    // other properties...
)