package com.niallmurph.jetweatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niallmurph.jetweatherforecast.model.entities.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_tbl")
    fun getFavourites() : Flow<List<Favourite>>

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    suspend fun getFavById(city : String) : Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourte(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)
}