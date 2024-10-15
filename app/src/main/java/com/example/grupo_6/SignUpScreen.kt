package com.example.grupo_6

import android.util.Log
import com.example.grupo_6.users // Agrega esta línea al principio de tu archivo
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Definir la clase de usuario
data class User(val username: String, val email: String, val password: String)

// Lista de usuarios global
val users = mutableListOf<User>()

@Composable
fun SignUpScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
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

        // Título "Sign Up"
        Text(
            text = "Sign Up",
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
            text = "Username",
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppins,
            color = Color(0xff7c7c7c),
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(0.9f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color.Black
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,   // Color del fondo cuando está enfocado
                unfocusedContainerColor = Color.Transparent  // Color del fondo cuando no está enfocado
            )
        )

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(25.dp))

        // Campo de email
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
            ),
            trailingIcon = {
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

        // Términos y políticas
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "By continuing you agree to our ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xff7c7c7c),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Terms of Service",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable {
                    navController.navigate("signIn")
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "and",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xff7c7c7c),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Privacy Policy",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable { /* Acción para mostrar términos y políticas */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                signUp(username, email, password) { response ->
                    responseMessage = response
                    Log.d("SignUpResponse", response)
                    if (response.contains("success")) {
                        navController.navigate("login")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Sign Up", fontSize = 18.sp, fontFamily = poppins, fontWeight = FontWeight.ExtraBold)
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Texto de "Already have an account?"
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xff7c7c7c),
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Log in",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = responseMessage, color = Color.Red)
    }
}

// Función para registrar usuarios
private fun signUp(username: String, email: String, password: String, onResponse: (String) -> Unit) {
    val newUser = User(username, email, password)

    // Verificar si el usuario ya existe
    if (users.any { it.email == email || it.username == username }) {
        onResponse("User already exists")
        return
    }

    // Agregar el nuevo usuario al array
    users.add(newUser)

    // Simulación de respuesta de éxito
    onResponse("Sign up success")
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
