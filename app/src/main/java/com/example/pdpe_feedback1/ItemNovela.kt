package com.example.pdpe_feedback1

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun ItemNovela(novela: Novela, onDelete: () -> Unit, onDetails: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(novela.titulo, fontWeight = FontWeight.Bold)
            Text(novela.autor)
        }
        Button(onClick = onDetails) {
            Text("Detalles")
        }
        Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text("Borrar novela")
        }
    }
}