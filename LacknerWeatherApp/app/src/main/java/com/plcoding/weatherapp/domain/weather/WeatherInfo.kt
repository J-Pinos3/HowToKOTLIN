package com.plcoding.weatherapp.domain.weather

import com.plcoding.weatherapp.data.remote.WeatherDataDto

//holds weather data per day
data class WeatherInfo(

    val weatherDataPerDay: Map<Int,List<WeatherData>>, //<current day index, weather data list of that day>
    val currentWeatherData: WeatherData //data for current day and hour
)
