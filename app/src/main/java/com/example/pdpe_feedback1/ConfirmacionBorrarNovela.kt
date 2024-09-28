package com.example.pdpe_feedback1

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun ConfirmacionBorrarNovela(onConfirm: () -> Unit, onCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Confirmar Borrado") },
        text = { Text("¿Está seguro de que desea borrar esta novela?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Sí")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("No")
            }
        }
    )
}