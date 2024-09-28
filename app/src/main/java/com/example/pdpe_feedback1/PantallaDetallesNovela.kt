package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun PantallaDetallesNovela(novela: Novela, onFavoriteToggle: (Novela) -> Unit, onAddResena: (Resena) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = novela.titulo, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Autor: ${novela.autor}")
        Text(text = "Año: ${novela.ano}")
        Text(text = "Sinopsis: ${novela.sinopsis}")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Favorita")
            Checkbox(
                checked = novela.esFavorita,
                onCheckedChange = { isChecked -> onFavoriteToggle(novela.copy(esFavorita = isChecked)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para añadir reseña
        var calificacion by remember { mutableStateOf(0) }
        var comentario by remember { mutableStateOf("") }
        Column {
            TextField(
                value = calificacion.toString(),
                onValueChange = { calificacion = it.toIntOrNull() ?: 0 },
                label = { Text("Calificación (1-10)") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = comentario,
                onValueChange = { comentario = it },
                label = { Text("Comentario") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (calificacion in 1..10 && comentario.isNotEmpty()) {
                    onAddResena(Resena(calificacion, comentario, "Usuario", System.currentTimeMillis()))
                }
            }) {
                Text("Añadir Reseña")
            }
        }
    }
}