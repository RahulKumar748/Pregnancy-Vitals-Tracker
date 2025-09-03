package com.janitri.pregnancyvitals.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.janitri.pregnancyvitals.ui.theme.DarkPurple
import com.janitri.pregnancyvitals.ui.theme.PurplePrimary
import com.janitri.pregnancyvitals.ui.theme.paddingLarge
import com.janitri.pregnancyvitals.ui.theme.paddingMedium
import com.janitri.pregnancyvitals.ui.theme.textMedium

@Composable
fun VitalInfo(icon: Painter, value: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        Image(
            painter = icon, contentDescription = null, modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(paddingMedium))
        Text(
            text = value, fontSize = textMedium, fontWeight = FontWeight.Bold, color = DarkPurple
        )
    }
}

@Composable
fun DialogTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = paddingLarge, top = 4.dp)
            )
        }
    }
}

@Composable
fun CustomButton(
    text: String, onClick: () -> Unit, width: Int = 133, height: Int = 40, cornerRadius: Int = 6
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(width = width.dp, height = height.dp),
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
    ) {
        Text(text)
    }
}

@Composable
fun FloatingAddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick, shape = CircleShape, containerColor = PurplePrimary
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}
