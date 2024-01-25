package com.niallmurph.jetweatherforecast.repository

import com.niallmurph.jetweatherforecast.data.WeatherDao
import com.niallmurph.jetweatherforecast.model.entities.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.niallmurph.jetweatherforecast.model.entities.Unit

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

    // Units

    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()

    suspend fun insertUnit(unit : Unit) =
        weatherDao.insertUnit(unit = unit)

    suspend fun updateUnit(unit : Unit) =
        weatherDao.updateUnit(unit = unit)

    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()

    suspend fun deleteUnit(unit : Unit) =
        weatherDao.deleteUnits(unit = unit)
}