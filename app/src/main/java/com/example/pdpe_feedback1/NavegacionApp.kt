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
        composable("agregarNovela") {
            PantallaAgregarNovela(navController) { nuevaNovela ->
                NovelaRepository.addNovela(nuevaNovela)
                navController.navigateUp()
            }
        }
        composable("detallesNovela/{titulo}") { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
            val novela = NovelaRepository.getNovelas().find { it.titulo == titulo } ?: return@composable
            PantallaDetallesNovela(novela, onFavoriteToggle = { updatedNovela ->
                NovelaRepository.updateNovela(updatedNovela)
            }, onAddResena = { resena ->
                ResenaRepository.addResena(novela, resena)
            })
        }
        composable("resenas") {
            val resenas = ResenaRepository.getAllResenas()
            PantallaResenas(novela = Novela("", "", 0, ""), resenas = resenas) { resena ->
                ResenaRepository.addResena(Novela("", "", 0, ""), resena)
            }
        }
    }
}