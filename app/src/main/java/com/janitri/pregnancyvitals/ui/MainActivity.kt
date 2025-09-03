@file:OptIn(ExperimentalMaterial3Api::class)

package com.janitri.pregnancyvitals.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.janitri.pregnancyvitals.R
import com.janitri.pregnancyvitals.ui.components.AddVitalsDialog
import com.janitri.pregnancyvitals.ui.components.FloatingAddButton
import com.janitri.pregnancyvitals.ui.components.TimerSection
import com.janitri.pregnancyvitals.ui.components.VitalList
import com.janitri.pregnancyvitals.ui.theme.TitleTextColor
import com.janitri.pregnancyvitals.ui.theme.VitalsTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalsTrackerTheme {
                val viewModel: VitalsViewModel = hiltViewModel()
                VitalsAppScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun VitalsAppScreen(viewModel: VitalsViewModel) {
    val vitalsList by viewModel.vitalsList.collectAsState()
    val isDialogShown by viewModel.isDialogShown.collectAsState()
    val timerText by viewModel.timerText.collectAsState()
    val isTimerRunning by viewModel.isTimerRunning.collectAsState()

    DisposableEffect(Unit) {
        viewModel.registerTimerReceiver()
        onDispose {
            viewModel.unregisterTimerReceiver()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.track_my_pregnancy)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = TitleTextColor
                )
            )
        },
        floatingActionButton = {
            FloatingAddButton(
                onClick = { viewModel.onAddVitalClicked() },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            TimerSection(
                timerText = timerText,
                isTimerRunning = isTimerRunning,
                onToggle = { viewModel.toggleTimer() }
            )
            VitalList(vitalsList)
        }

        if (isDialogShown) {
            AddVitalsDialog(
                onDismiss = { viewModel.onDialogDismiss() },
                onSubmit = { sys, dia, weight, kicks ->
                    viewModel.addVital(sys, dia, weight, kicks)
                }
            )
        }
    }
}