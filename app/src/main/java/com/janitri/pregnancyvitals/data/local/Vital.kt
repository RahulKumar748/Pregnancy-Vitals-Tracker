package com.janitri.pregnancyvitals.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.janitri.pregnancyvitals.util.Constants.VITALS_TABLE_NAME

@Entity(tableName = VITALS_TABLE_NAME)
data class Vital(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val systolicBP: Int,
    val diastolicBP: Int,
    val heartRate: Int,
    val weight: Double,
    val babyKicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)