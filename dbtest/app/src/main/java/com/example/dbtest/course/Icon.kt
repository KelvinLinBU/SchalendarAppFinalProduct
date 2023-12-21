package com.example.dbtest.course

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.dbtest.R

// implement the weather icons
@Composable
fun WeatherIconNotSure() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather),
        contentDescription = "weather"
    )
}
@Composable
fun WeatherIconHot() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weathre_hot),
        contentDescription = "weather hot"
    )
}
@Composable
fun WeatherIconWarm() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather_warm),
        contentDescription = "weather warm"
    )
}
@Composable
fun WeatherIconPle() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather_ple),
        contentDescription = "weather ple"
    )
}
@Composable
fun WeatherIconCool() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather_cool),
        contentDescription = "weather cool"
    )
}
@Composable
fun WeatherIconChil() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather_chil),
        contentDescription = "weather chil"
    )
}
@Composable
fun WeatherIconCold() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather_cold),
        contentDescription = "weather cold"
    )
}