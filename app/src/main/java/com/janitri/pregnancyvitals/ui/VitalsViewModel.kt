package com.janitri.pregnancyvitals.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.janitri.pregnancyvitals.data.local.Vital
import com.janitri.pregnancyvitals.data.repository.VitalsRepository
import com.janitri.pregnancyvitals.service.TimerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class VitalsViewModel @Inject constructor(
    private val repository: VitalsRepository,
    application: Application
) : AndroidViewModel(application) {

    // PART I: Vitals List State
    val vitalsList = repository.allVitals.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _isDialogShown = MutableStateFlow(false)
    val isDialogShown = _isDialogShown.asStateFlow()

    fun onAddVitalClicked() {
        _isDialogShown.value = true
    }

    fun onDialogDismiss() {
        _isDialogShown.value = false
    }

    fun addVital(systolicBP: String, diastolicBP: String, weight: String, babyKicks: String) {
        viewModelScope.launch {
            val vital = Vital(
                systolicBP = systolicBP.toIntOrNull() ?: 0,
                diastolicBP = diastolicBP.toIntOrNull() ?: 0,
                heartRate = (60..100).random(), // Generating random heart rate as it's not in the dialog
                weight = weight.toDoubleOrNull() ?: 0.0,
                babyKicks = babyKicks.toIntOrNull() ?: 0
            )
            repository.insert(vital)
            _isDialogShown.value = false
        }
    }

    // PART II: Timer State
    private val _timerText = MutableStateFlow("00:00")
    val timerText = _timerText.asStateFlow()

    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning = _isTimerRunning.asStateFlow()

    private val timerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                if (it.action == TimerService.TIMER_UPDATED) {
                    val timeElapsed = it.getLongExtra(TimerService.TIME_ELAPSED, 0L)
                    _timerText.value = formatTime(timeElapsed)
                    _isTimerRunning.value = true
                }
            }
        }
    }

    fun registerTimerReceiver() {
        ContextCompat.registerReceiver(
            getApplication<Application>(),
            timerReceiver,
            IntentFilter(TimerService.TIMER_UPDATED),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    fun unregisterTimerReceiver() {
        getApplication<Application>().unregisterReceiver(timerReceiver)
    }

    fun toggleTimer() {
        val context = getApplication<Application>()
        val serviceIntent = Intent(context, TimerService::class.java)
        if (_isTimerRunning.value) {
            context.stopService(serviceIntent)
            _isTimerRunning.value = false
            _timerText.value = "00:00"
        } else {
            context.startService(serviceIntent)
        }
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(seconds: Long): String {
        val minutes = TimeUnit.SECONDS.toMinutes(seconds)
        val remainingSeconds = seconds - TimeUnit.MINUTES.toSeconds(minutes)
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        // Ensure receiver is unregistered to prevent leaks
        try {
            unregisterTimerReceiver()
        } catch (e: Exception) {
            // Can throw if not registered, ignore
        }
    }
}