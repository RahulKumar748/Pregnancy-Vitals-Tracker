package com.janitri.pregnancyvitals.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janitri.pregnancyvitals.R
import com.janitri.pregnancyvitals.ui.theme.TimerGreen
import com.janitri.pregnancyvitals.ui.theme.TimerRed
import com.janitri.pregnancyvitals.ui.theme.WhiteTextColor
import com.janitri.pregnancyvitals.ui.theme.paddingLarge

@Composable
fun TimerSection(timerText: String, isTimerRunning: Boolean, onToggle: () -> Unit) {
    Box(
        modifier = Modifier
            .height(54.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(paddingLarge),
                text = stringResource(R.string.timer, timerText),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = WhiteTextColor
            )
            Button(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 18.dp,
                    bottom = 8.dp
                ),
                onClick = onToggle,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isTimerRunning) TimerRed else TimerGreen
                )
            ) {
                Text(if (isTimerRunning) stringResource(R.string.stop) else stringResource(R.string.start))
            }
        }
    }
}