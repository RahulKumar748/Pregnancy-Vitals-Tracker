package com.janitri.pregnancyvitals.di

import android.content.Context
import androidx.room.Room
import com.janitri.pregnancyvitals.data.local.VitalDao
import com.janitri.pregnancyvitals.data.local.VitalsDatabase
import com.janitri.pregnancyvitals.data.repository.VitalsRepository
import com.janitri.pregnancyvitals.util.Constants.VITALS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVitalsDatabase(@ApplicationContext context: Context): VitalsDatabase {
        return Room.databaseBuilder(
            context,
            VitalsDatabase::class.java,
            VITALS_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideVitalDao(database: VitalsDatabase): VitalDao {
        return database.vitalDao()
    }

    @Provides
    @Singleton
    fun provideVitalsRepository(vitalDao: VitalDao): VitalsRepository {
        return VitalsRepository(vitalDao)
    }
}