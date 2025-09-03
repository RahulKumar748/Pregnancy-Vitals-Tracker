package com.janitri.pregnancyvitals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.janitri.pregnancyvitals.R
import com.janitri.pregnancyvitals.data.local.Vital
import com.janitri.pregnancyvitals.ui.theme.LightPurple
import com.janitri.pregnancyvitals.ui.theme.PurplePrimary
import com.janitri.pregnancyvitals.ui.theme.WhiteTextColor
import com.janitri.pregnancyvitals.ui.theme.cornerLarge
import com.janitri.pregnancyvitals.ui.theme.paddingLarge
import com.janitri.pregnancyvitals.ui.theme.paddingMedium
import com.janitri.pregnancyvitals.ui.theme.textMedium
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun VitalListItem(vital: Vital) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(cornerLarge),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = LightPurple)
    ) {
        Column {
            Column(modifier = Modifier.padding(paddingLarge)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VitalInfo(
                        icon = painterResource(id = R.drawable.heart_rate),
                        value = "${vital.heartRate} bpm",
                        modifier = Modifier.weight(1f)
                    )
                    VitalInfo(
                        icon = painterResource(id = R.drawable.blood_pressure),
                        value = stringResource(R.string.mmhg, vital.systolicBP, vital.diastolicBP),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(paddingMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VitalInfo(
                        icon = painterResource(id = R.drawable.scale),
                        value = stringResource(R.string.kg, vital.weight),
                        modifier = Modifier.weight(1f)
                    )
                    VitalInfo(
                        icon = painterResource(id = R.drawable.kicks),
                        value = stringResource(R.string.kicks, vital.babyKicks),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(PurplePrimary),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = SimpleDateFormat("EEE, d MMM yyyy hh:mm a", Locale.getDefault())
                        .format(Date(vital.timestamp)),
                    color = WhiteTextColor,
                    fontSize = textMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}
