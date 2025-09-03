package com.janitri.pregnancyvitals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.janitri.pregnancyvitals.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVital(vital: Vital)

    @Query("SELECT * FROM ${Constants.VITALS_TABLE_NAME} ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<Vital>>
}