package com.work.thirukkural.di

import android.content.Context
import com.work.thirukkural.data.AppDataBase
import com.work.thirukkural.data.dao.AdhigaramsDao
import com.work.thirukkural.data.dao.FavoriteAdhigaramlDao
import com.work.thirukkural.data.dao.FavoriteKuralDao
import com.work.thirukkural.data.dao.KuralDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object DataModule {

    @Provides
    fun provideKuralDao(database: AppDataBase): KuralDao {
        return database.kuralDao()
    }

    @Provides
    fun provideAdhigaramsDao(database: AppDataBase): AdhigaramsDao {
        return database.adhigaramsDao()
    }

    @Provides
    fun provideFavoriteAdhigaramsDao(database: AppDataBase): FavoriteAdhigaramlDao {
        return database.favoriteAdhigaramDao()
    }

    @Provides
    fun provideFavoriteKuralsDao(database: AppDataBase): FavoriteKuralDao {
        return database.favoriteKuralDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getDatabase(context)
    }
}