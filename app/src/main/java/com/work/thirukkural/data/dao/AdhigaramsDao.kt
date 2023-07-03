package com.work.thirukkural.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
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

    @Query("SELECT * FROM Adhigarams WHERE Name LIKE :query")
    suspend fun getAdhigarams(query: String): List<Adhigaram>

    @Query("SELECT * FROM Adhigarams WHERE Number >= :start AND Number <= :end")
    suspend fun getAdhigarams(start: Int?=1, end: Int?=10): List<Adhigaram>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(adhigaram: Adhigaram): Int

}