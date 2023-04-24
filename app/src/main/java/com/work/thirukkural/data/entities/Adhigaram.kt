package com.work.thirukkural.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Adhigarams")
data class Adhigaram(
    @ColumnInfo(name = "PartName") val partName: String?,
    @ColumnInfo(name = "IyalName") val iyalName: String?,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Number") @PrimaryKey val number: Int?,
    @ColumnInfo(name = "Start") val start: Int?=1,
    @ColumnInfo(name = "End") val end: Int?=10,
    @ColumnInfo(name = "Favorite") var favorite: Int? = 0,
)