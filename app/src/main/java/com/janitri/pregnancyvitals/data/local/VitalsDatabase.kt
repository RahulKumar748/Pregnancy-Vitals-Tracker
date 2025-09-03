package com.janitri.pregnancyvitals.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vital::class], version = 1, exportSchema = false)
abstract class VitalsDatabase : RoomDatabase() {
    abstract fun vitalDao(): VitalDao
}