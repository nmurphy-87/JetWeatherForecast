package com.niallmurph.jetweatherforecast.repository

import android.util.Log
import com.niallmurph.jetweatherforecast.data.DataOrException
import com.niallmurph.jetweatherforecast.model.Weather
import com.niallmurph.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherAPI) {

    suspend fun getWeather(cityQuery : String) : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch(e : Exception){
            Log.d("ESOTERIC", "getWeather : $e")
            return DataOrException(e = e)
        }
        Log.d("ESOTERIC", "getWeather : ${response.city.name}")
        return DataOrException(data = response)
    }
}