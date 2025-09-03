package com.janitri.pregnancyvitals.data.repository

import com.janitri.pregnancyvitals.data.local.Vital
import com.janitri.pregnancyvitals.data.local.VitalDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VitalsRepository @Inject constructor(private val vitalDao: VitalDao) {

    val allVitals: Flow<List<Vital>> = vitalDao.getAllVitals()

    suspend fun insert(vital: Vital) {
        vitalDao.insertVital(vital)
    }
}