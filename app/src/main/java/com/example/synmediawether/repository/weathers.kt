package com.example.synmediawether.repository

import androidx.compose.ui.res.painterResource
import com.example.synmediawether.R
import com.example.synmediawether.pojo.WeatherTypes

object weathers {
    val data = listOf(
        WeatherTypes("Clear",  R.drawable.sunny),
        WeatherTypes("Clouds",  R.drawable.clouds),
        WeatherTypes("Rain",  R.drawable.rain),
        WeatherTypes("Drizzle",  R.drawable.secondcloud),
        WeatherTypes("Thunderstorm",  R.drawable.thunder),
        WeatherTypes("Snow",  R.drawable.snow),
        WeatherTypes("Mist",  R.drawable.snow),
        WeatherTypes("Fog",  R.drawable.snow),
        WeatherTypes("Dust",  R.drawable.sunny),
        WeatherTypes("Sand",  R.drawable.sunny),
        WeatherTypes("Ash",  R.drawable.sunny),
        WeatherTypes("Squall",  R.drawable.snow),
        WeatherTypes("Tornado",  R.drawable.strom),

    )
}