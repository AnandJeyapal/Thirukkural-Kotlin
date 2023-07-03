package com.work.thirukkural.repository

import com.work.thirukkural.data.dao.KuralDao
import com.work.thirukkural.data.entities.Kural
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KuralsRepository @Inject constructor(private val kuralDao: KuralDao) {

    suspend fun getKurals(start: Int, end: Int): List<Kural> {
        return kuralDao.getKurals(start, end)
    }

    suspend fun getKurals(query: String): List<Kural> {
        return kuralDao.getKurals("%$query%")
    }

    suspend fun getKural(kuralId: Int): Kural {
        return kuralDao.getKural(kuralId)
    }

    fun fetchKural(kuralId: Int): Flow<Kural> {
        return kuralDao.fetchKural(kuralId)
    }


    suspend fun updateFavorite(kuralId : Int): Boolean? {
        val kural  = kuralDao.getKural(kuralId)
        val favorite = kural.favorite
        kural.favorite = if(favorite == 1) 0 else 1
        val rows = kuralDao.update(kural)
        if(rows > 0) {
            return kural.favorite == 1
        }
        return null
    }

    fun getFavoriteKurals() = kuralDao.getFavoriteKurals()
}