package com.work.thirukkural.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.work.thirukkural.data.dao.AdhigaramsDao
import com.work.thirukkural.data.dao.FavoriteAdhigaramlDao
import com.work.thirukkural.data.dao.FavoriteKuralDao
import com.work.thirukkural.data.dao.KuralDao
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.FavoriteAdhigaram
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.data.entities.FavoriteKural
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Kural::class, FavoriteAdhigaram::class, Adhigaram::class, FavoriteKural::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun kuralDao() : KuralDao
    abstract fun adhigaramsDao() : AdhigaramsDao
    abstract fun favoriteAdhigaramDao() : FavoriteAdhigaramlDao
    abstract fun favoriteKuralDao() : FavoriteKuralDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(
            context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "all_kurals.db")
                    .createFromAsset("kurals.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}