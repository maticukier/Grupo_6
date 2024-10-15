package com.example.grupo_6

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var responseMessage by remember { mutableStateOf("") }

    val poppins = FontFamily(
        androidx.compose.ui.text.font.Font(R.font.poppins_regular),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp)) // Ajustar espacio

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Tu logo aquí
            contentDescription = "Logo",
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(90.dp))

        // Título "Log In"
        Text(
            text = "Log In",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppins,
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Subtítulo
        Text(
            text = "Enter your credentials to continue",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins,
            color = Color(0xff7c7c7c),
        )
        Spacer(modifier = Modifier.height(50.dp))

        // Campo de usuario
        Text(
            text = "Email",
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppins,
            color = Color(0xff7c7c7c),
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(0.9f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color.Black
            ),trailingIcon = {
                if (email.endsWith("@gmail.com")) {
                    Image(
                        painter = painterResource(id = R.drawable.tick),
                        contentDescription = "Tick",
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,   // Color del fondo cuando está enfocado
                unfocusedContainerColor = Color.Transparent  // Color del fondo cuando no está enfocado
            )
        )

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(25.dp))

        // Campo de contraseña
        Text(
            text = "Password",
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppins,
            color = Color(0xff7c7c7c),
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(0.9f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color.Black
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) R.drawable.visual_on else R.drawable.visual_off
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Toggle Password Visibility",
                    modifier = Modifier
                        .clickable { passwordVisible = !passwordVisible }
                        .size(20.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,   // Color del fondo cuando está enfocado
                unfocusedContainerColor = Color.Transparent  // Color del fondo cuando no está enfocado
            )
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(20.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                login(email, password) { response ->
                    responseMessage = response
                    Log.d("LoginResponse", response)
                    if (response.contains("success")) {
                        // Navegar a la pantalla principal o dashboard
                        navController.navigate("location") {
                            popUpTo("login") { inclusive = true } // Eliminar la pantalla de login de la pila
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Log In", fontSize = 18.sp, fontFamily = poppins, fontWeight = FontWeight.ExtraBold)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Texto de "Don't have an account?"
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xff7c7c7c),
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Sign up",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable {
                    navController.navigate("signUp")
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = responseMessage, color = Color.Red)
    }
}

// Función para iniciar sesión
private fun login(email: String, password: String, onResponse: (String) -> Unit) {
    val user = users.find { it.email == email && it.password == password }
    if (user != null) {
        onResponse("Login success")
    } else {
        onResponse("Invalid username or password")
    }
}