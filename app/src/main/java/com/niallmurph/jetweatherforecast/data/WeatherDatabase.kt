package com.niallmurph.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niallmurph.jetweatherforecast.model.entities.Favourite
import com.niallmurph.jetweatherforecast.model.entities.Unit

@Database(entities = [Favourite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao() : WeatherDao
}