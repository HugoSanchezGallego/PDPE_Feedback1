package com.example.pdpe_feedback1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun PantallaLogin(navController: NavController, onLoginSuccess: (String) -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (AuthManager.authenticate(usuario, contrasena)) {
                onLoginSuccess(usuario)
            } else {
                mensajeError = "Usuario o contraseña incorrectos"
            }
        }) {
            Text("Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navController.navigate("registro")
        }) {
            Text("Registrarse")
        }
        if (mensajeError.isNotEmpty()) {
            Text(mensajeError, color = Color.Red)
        }
    }
}