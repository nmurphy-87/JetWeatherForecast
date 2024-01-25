package com.niallmurph.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niallmurph.jetweatherforecast.model.entities.Favourite

@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao() : WeatherDao
}