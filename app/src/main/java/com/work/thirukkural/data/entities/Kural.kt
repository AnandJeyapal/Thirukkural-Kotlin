package com.work.thirukkural.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kurals")
data class Kural(
    @PrimaryKey val id: Int?,
    val kural: String?,
    val first_exp: String?,
    val second_exp: String?,
    val third_exp: String?,
     @ColumnInfo(name = "Favorite") var favorite: Int? = 0,
)