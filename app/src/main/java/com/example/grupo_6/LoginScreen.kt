package com.example.grupo_6

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginResponse by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Título "Sign In"
        Text(
            text = "Sign In",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Subtítulo
        Text(
            text = "Enter your email and password",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // "Forgot Password?" (alineado a la derecha)
        Text(
            text = "Forgot Password?",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.End).padding(end = 40.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de iniciar sesión
        Button(
            onClick = { login(username, password) { response ->
                loginResponse = response
                Log.d("LoginResponse", response) // Log para la respuesta
            } },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Log In", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de respuesta
        // Text(text = loginResponse, fontSize = 14.sp)

        // "Don’t have an account? Signup"
        Text(
            text = "Don't have an account? Signup",
            fontSize = 14.sp
        )
    }
}

private fun login(username: String, password: String, onResponse: (String) -> Unit) {
    val client = OkHttpClient()
    val json = """{"username": "$username", "password": "$password"}"""
    val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
    val request = Request.Builder()
        .url("https://fakestoreapi.com/auth/login")
        .post(requestBody)
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar error
                Log.e("LoginError", "Error: ${e.message}") // Log para error
                onResponse("Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        Log.e("LoginError", "Unexpected code $it") // Log para código inesperado
                        onResponse("Unexpected code $it")
                        return
                    }

                    // Procesar la respuesta
                    val responseData = it.body?.string() ?: "No response"
                    Log.d("LoginResponse", responseData) // Log para respuesta exitosa
                    onResponse(responseData)
                }
            }
        })
    }
}
