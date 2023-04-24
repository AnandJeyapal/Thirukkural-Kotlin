package com.work.thirukkural.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.work.thirukkural.data.entities.Adhigaram
import kotlinx.coroutines.flow.Flow

@Dao
interface AdhigaramsDao {

    @Query("SELECT * FROM Adhigarams")
    suspend fun getAll(): List<Adhigaram>

    @Query("SELECT * FROM Adhigarams WHERE Favorite = 1")
    fun getFavoriteAdhigarams(): Flow<List<Adhigaram>>

    @Query("SELECT * FROM Adhigarams WHERE Number = :adhigaramId")
    suspend fun getAdhigaram(adhigaramId: Int): Adhigaram

    @Query("SELECT * FROM Adhigarams WHERE Number = :adhigaramId")
    fun loadAdhigaram(adhigaramId: Int): Flow<Adhigaram>

    @Query("SELECT * FROM Adhigarams WHERE PartName LIKE :paal")
    suspend fun getAllForPaal(paal: String): List<Adhigaram>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(adhigaram: Adhigaram): Int

}