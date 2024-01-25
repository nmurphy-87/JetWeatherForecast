package com.niallmurph.jetweatherforecast.model.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favourite(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city : String,

    @ColumnInfo(name = "country")
    val country : String,
)
