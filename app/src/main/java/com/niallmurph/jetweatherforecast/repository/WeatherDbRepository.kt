package com.niallmurph.jetweatherforecast.repository

import com.niallmurph.jetweatherforecast.data.WeatherDao
import com.niallmurph.jetweatherforecast.model.entities.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()

    suspend fun insertFavourite(favourite: Favourite) =
        weatherDao.insertFavourte(favourite = favourite)

    suspend fun updateFavourite(favourite: Favourite) =
        weatherDao.updateFavourite(favourite = favourite)

    suspend fun deleteAllFavourites() = weatherDao.deleteAllFavourites()

    suspend fun deleteFavourite(favourite: Favourite) =
        weatherDao.deleteFavourite(favourite = favourite)

    suspend fun getFavById(city: String) = weatherDao.getFavById(city = city)
}