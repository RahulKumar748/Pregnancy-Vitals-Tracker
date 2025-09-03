package com.janitri.pregnancyvitals.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.janitri.pregnancyvitals.R
import com.janitri.pregnancyvitals.ui.theme.PurplePrimary
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.janitri.pregnancyvitals.ui.theme.DarkPurple

@Composable
fun AddVitalsDialog(
    onDismiss: () -> Unit,
    onSubmit: (String, String, String, String) -> Unit
) {
    var sysBp by rememberSaveable { mutableStateOf("") }
    var diaBp by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var babyKicks by rememberSaveable { mutableStateOf("") }

    var showError by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.add_vitals),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = DarkPurple,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    DialogTextField(
                        value = sysBp,
                        onValueChange = { sysBp = it },
                        label = stringResource(R.string.sys_bp),
                        modifier = Modifier.weight(1f),
                        isError = showError && sysBp.isBlank()
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DialogTextField(
                        value = diaBp,
                        onValueChange = { diaBp = it },
                        label = stringResource(R.string.dia_bp),
                        modifier = Modifier.weight(1f),
                        isError = showError && diaBp.isBlank()
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                DialogTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = stringResource(R.string.weight_in_kg),
                    isError = showError && weight.isBlank()
                )
                Spacer(modifier = Modifier.height(12.dp))
                DialogTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = stringResource(R.string.baby_kicks),
                    isError = showError && babyKicks.isBlank()
                )
                Spacer(modifier = Modifier.height(24.dp))

                CustomButton(
                    text = stringResource(R.string.submit),
                    onClick = {
                        if (sysBp.isNotBlank() &&
                            diaBp.isNotBlank() &&
                            weight.isNotBlank() &&
                            babyKicks.isNotBlank()
                        ) {
                            showError = false
                            onSubmit(sysBp, diaBp, weight, babyKicks)
                        } else {
                            showError = true
                        }
                    }
                )
            }
        }
    }
}
