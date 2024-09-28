package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetallesNovela(navController: NavController, novela: Novela, usuario: String, onFavoriteToggle: (Novela) -> Unit) {
    var resenas by remember { mutableStateOf(ResenaRepository.getResenasByTitulo(novela.titulo)) }
    var calificacion by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de Novela") },
                navigationIcon = {
                    Button(onClick = { navController.navigateUp() }) {
                        Text("Volver")
                    }
                },
                actions = {
                    Button(onClick = { navController.navigate("login") }) {
                        Text("Cerrar Sesión")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            // Mostrar reseñas
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(resenas) { resena ->
                    ItemResena(resena)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Formulario para añadir reseña
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
                        ResenaRepository.addResena(Resena(calificacion, comentario, usuario, System.currentTimeMillis(), novela.titulo))
                        resenas = ResenaRepository.getResenasByTitulo(novela.titulo)
                        calificacion = 0
                        comentario = ""
                    } else {
                        mensajeError = "Calificación debe estar entre 1 y 10 y comentario no puede estar vacío"
                    }
                }) {
                    Text("Añadir Reseña")
                }
                if (mensajeError.isNotEmpty()) {
                    Text(mensajeError, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun ItemResena(resena: Resena) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Calificación: ${resena.calificacion}", fontWeight = FontWeight.Bold)
            Text("Comentario: ${resena.comentario}")
            Text("Usuario: ${resena.usuario}")
        }
    }
}