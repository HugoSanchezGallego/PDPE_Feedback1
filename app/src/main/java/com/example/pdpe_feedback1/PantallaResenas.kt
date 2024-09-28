package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaResenas(novela: Novela, resenas: List<Resena>, onAddResena: (Resena) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Reseñas de ${novela.titulo}", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(resenas) { resena ->
                ItemResena(resena)
            }
        }
        // Formulario para añadir reseña
        var calificacion by remember { mutableStateOf(0) }
        var comentario by remember { mutableStateOf("") }
        Button(onClick = {
            if (calificacion in 1..10 && comentario.isNotEmpty()) {
                onAddResena(Resena(calificacion, comentario, "Usuario", System.currentTimeMillis()))
            }
        }) {
            Text("Añadir Reseña")
        }
    }
}

@Composable
fun ItemResena(resena: Resena) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Calificación: ${resena.calificacion}")
        Text(text = "Comentario: ${resena.comentario}")
        Text(text = "Por: ${resena.usuario} el ${resena.timestamp}")
    }
}