package com.niallmurph.jetweatherforecast.repository

import com.niallmurph.jetweatherforecast.data.DataOrException
import com.niallmurph.jetweatherforecast.model.WeatherObject
import com.niallmurph.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherAPI) {

    suspend fun getWeather(cityQuery : String) : DataOrException<WeatherObject, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch(e : Exception){
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}