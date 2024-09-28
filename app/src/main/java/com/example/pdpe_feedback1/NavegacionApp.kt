package com.example.pdpe_feedback1

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavegacionApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") {
            PantallaLogin(navController) { usuario ->
                navController.navigate("principal/$usuario")
            }
        }
        composable("registro") {
            PantallaRegistro(navController)
        }
        composable("principal/{usuario}") { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""
            PantallaPrincipal(navController, usuario)
        }
        composable("agregarNovela/{usuario}") { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""
            PantallaAgregarNovela(navController, usuario) { nuevaNovela ->
                NovelaRepository.addNovela(usuario, nuevaNovela)
                navController.navigate("principal/$usuario")
            }
        }
        composable("detallesNovela/{titulo}/{usuario}") { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
            val usuario = backStackEntry.arguments?.getString("usuario") ?: ""
            val novela = NovelaRepository.getNovelas(usuario).find { it.titulo == titulo } ?: return@composable
            PantallaDetallesNovela(navController, novela, usuario, onFavoriteToggle = { updatedNovela ->
                NovelaRepository.updateNovela(usuario, updatedNovela)
            })
        }
    }
}