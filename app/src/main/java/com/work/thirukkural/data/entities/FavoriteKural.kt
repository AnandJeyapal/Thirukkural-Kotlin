package com.work.thirukkural.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_kurals")
data class FavoriteKural(
    @ColumnInfo(name = "Number") @PrimaryKey val number: Int?,
)