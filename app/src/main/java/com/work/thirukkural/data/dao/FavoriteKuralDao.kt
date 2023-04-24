package com.work.thirukkural.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.work.thirukkural.data.entities.FavoriteKural
import com.work.thirukkural.data.entities.Kural
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteKuralDao {

    @Query("SELECT * FROM favorite_kurals")
    fun getAll(): Flow<List<FavoriteKural>>

}