package com.work.thirukkural.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.work.thirukkural.data.entities.FavoriteAdhigaram
import com.work.thirukkural.data.entities.Kural
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAdhigaramlDao {

    @Query("SELECT * FROM favorite_adhigarams")
    fun getAll(): Flow<List<FavoriteAdhigaram>>

}