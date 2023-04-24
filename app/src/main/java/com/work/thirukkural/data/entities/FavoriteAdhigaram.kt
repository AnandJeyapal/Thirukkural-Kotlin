package com.work.thirukkural.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_adhigarams")
data class FavoriteAdhigaram(
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Number") @PrimaryKey val number: Int?,
)