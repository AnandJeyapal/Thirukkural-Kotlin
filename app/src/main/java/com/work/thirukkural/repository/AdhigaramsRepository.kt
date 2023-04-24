package com.work.thirukkural.repository

import com.work.thirukkural.data.dao.AdhigaramsDao
import com.work.thirukkural.data.entities.Adhigaram
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdhigaramsRepository @Inject constructor(private val adhigaramsDao: AdhigaramsDao) {

    suspend fun getAdhigaramsForPaal(paal: String): List<Adhigaram> {
        return adhigaramsDao.getAllForPaal(paal)
    }

    fun getAdhigaram(adhigaramId: Int): Flow<Adhigaram> {
        return adhigaramsDao.loadAdhigaram(adhigaramId)
    }

    suspend fun updateFavorite(adhigaramId : Int): Boolean? {
        val adhigaram  = adhigaramsDao.getAdhigaram(adhigaramId)
        val favorite = adhigaram.favorite
        adhigaram.favorite = if(favorite == 1) 0 else 1
        val rows = adhigaramsDao.update(adhigaram)
        if(rows > 0) {
            return adhigaram.favorite == 1
        }
        return null
    }

    fun getFavoriteAdhigarams() = adhigaramsDao.getFavoriteAdhigarams()
}