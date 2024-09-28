package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun PantallaPrincipal(navController: NavController, usuario: String) {
    var novelas by remember { mutableStateOf(NovelaRepository.getNovelas()) }
    var orden by remember { mutableStateOf(Orden.TITULO_ASC) }
    var novelaAEliminar by remember { mutableStateOf<Novela?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Bienvenido, $usuario", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        // Opciones de ordenamiento
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { orden = Orden.TITULO_ASC }) { Text("Título Asc") }
            Button(onClick = { orden = Orden.TITULO_DESC }) { Text("Título Desc") }
            Button(onClick = { orden = Orden.AUTOR_ASC }) { Text("Autor Asc") }
            Button(onClick = { orden = Orden.AUTOR_DESC }) { Text("Autor Desc") }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para añadir novela
        Button(onClick = { navController.navigate("agregarNovela") }) {
            Text("Añadir Novela")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ver reseñas
        Button(onClick = { navController.navigate("resenas") }) {
            Text("Ver Reseñas")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Lista de novelas
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(novelas.sortedWith(orden.comparator)) { novela ->
                ItemNovela(
                    novela,
                    onDelete = { novelaAEliminar = novela },
                    onDetails = { navController.navigate("detallesNovela/${novela.titulo}") }
                )
            }
        }

        // Diálogo de confirmación de borrado
        novelaAEliminar?.let { novela ->
            ConfirmacionBorrarNovela(
                onConfirm = {
                    NovelaRepository.deleteNovela(novela)
                    novelas = NovelaRepository.getNovelas()
                    novelaAEliminar = null
                },
                onCancel = { novelaAEliminar = null }
            )
        }
    }
}