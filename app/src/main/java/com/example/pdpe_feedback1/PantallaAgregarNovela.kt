package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color // Add this import

@Composable
fun PantallaAgregarNovela(navController: NavController, onAddNovela: (Novela) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") }
        )
        TextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor") }
        )
        TextField(
            value = ano,
            onValueChange = { ano = it },
            label = { Text("Año") }
        )
        TextField(
            value = sinopsis,
            onValueChange = { sinopsis = it },
            label = { Text("Sinopsis") }
        )
        Button(onClick = {
            try {
                val anoInt = ano.toInt()
                val nuevaNovela = Novela(titulo, autor, anoInt, sinopsis)
                onAddNovela(nuevaNovela)
                navController.navigateUp()
            } catch (e: NumberFormatException) {
                mensajeError = "El año debe ser un número"
            } catch (e: DatosIncompletosException) {
                mensajeError = e.message ?: "Datos incompletos"
            }
        }) {
            Text("Añadir Novela")
        }
        if (mensajeError.isNotEmpty()) {
            Text(mensajeError, color = Color.Red)
        }
    }
}