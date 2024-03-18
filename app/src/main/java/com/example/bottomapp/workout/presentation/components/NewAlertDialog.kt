package com.example.bottomapp.workout.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewAlertDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    goBack: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { goBack() },
        confirmButton = { Button(onClick = onConfirm) {
            Text(text = "Continua allenamento")
        } },
        dismissButton = { Button(onClick = onDismiss) {
            Text(text = "Sì, scarta")
        }},
        title = { Text(text = "Allenamento in corso") },
        text = { Text(text = "Vecchio allenamento ancora in corso. Vuoi scartarlo o continuarlo? ")}
    )
}