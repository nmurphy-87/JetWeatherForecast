package com.niallmurph.jetweatherforecast.model

data class WeatherItem(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherObject>
)