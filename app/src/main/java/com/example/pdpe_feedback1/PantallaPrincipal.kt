package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(navController: NavController, usuario: String) {
    var novelas by remember { mutableStateOf(NovelaRepository.getNovelas(usuario)) }
    var orden by remember { mutableStateOf(Orden.TITULO_ASC) }
    var novelaAEliminar by remember { mutableStateOf<Novela?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Principal") },
                actions = {
                    Button(onClick = { navController.navigate("login") }) {
                        Text("Cerrar Sesión")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("Bienvenido, $usuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            // Opciones de ordenamiento
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { orden = Orden.TITULO_ASC },
                    modifier = Modifier.weight(1f).height(72.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Título")
                        Text("Asc")
                    }
                }
                Button(
                    onClick = { orden = Orden.TITULO_DESC },
                    modifier = Modifier.weight(1f).height(72.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Título")
                        Text("Desc")
                    }
                }
                Button(
                    onClick = { orden = Orden.AUTOR_ASC },
                    modifier = Modifier.weight(1f).height(72.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Autor")
                        Text("Asc")
                    }
                }
                Button(
                    onClick = { orden = Orden.AUTOR_DESC },
                    modifier = Modifier.weight(1f).height(72.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Autor")
                        Text("Desc")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para añadir novela
            Button(onClick = { navController.navigate("agregarNovela/$usuario") }) {
                Text("Añadir Novela")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de novelas
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(novelas.sortedWith(orden.comparator)) { novela ->
                    ItemNovela(
                        novela,
                        onDelete = { novelaAEliminar = novela },
                        onDetails = { navController.navigate("detallesNovela/${novela.titulo}/$usuario") }
                    )
                }
            }

            // Diálogo de confirmación de borrado
            novelaAEliminar?.let { novela ->
                ConfirmacionBorrarNovela(
                    onConfirm = {
                        NovelaRepository.deleteNovela(usuario, novela)
                        novelas = NovelaRepository.getNovelas(usuario)
                        novelaAEliminar = null
                    },
                    onCancel = { novelaAEliminar = null }
                )
            }
        }
    }
}