package com.work.thirukkural.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
import kotlinx.coroutines.flow.Flow

@Dao
interface KuralDao {

    @Query("SELECT * FROM kurals")
    fun getAll(): Flow<List<Kural>>

    @Query("SELECT * FROM kurals WHERE id >= :start AND id <= :end")
    suspend fun getKurals(start: Int?=1, end: Int?=10): List<Kural>

    @Query("SELECT * FROM kurals WHERE id = :kuralId")
    suspend fun getKural(kuralId: Int): Kural

    @Query("SELECT * FROM kurals WHERE id = :kuralId")
    fun fetchKural(kuralId: Int): Flow<Kural>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(kural: Kural): Int

    @Query("SELECT * FROM kurals WHERE Favorite = 1")
    fun getFavoriteKurals(): Flow<List<Kural>>

}