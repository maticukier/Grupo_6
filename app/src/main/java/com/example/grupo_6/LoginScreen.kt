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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginResponse by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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


        // Título "Sign In"
        Text(
            text = "Sign In",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = poppins,
        )
        Spacer(modifier = Modifier.height(5.dp))


        // Subtítulo
        Text(
            text = "Enter your emails and password",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins,
            color = Color(0xff7c7c7c),
        )
        Spacer(modifier = Modifier.height(50.dp))




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
        Spacer(modifier = Modifier.height(25.dp))


        // Botón de iniciar sesión
        Button(
            onClick = {
                login(email, password) { response ->
                    loginResponse = response
                    Log.d("LoginResponse", response) // Log para la respuesta
                    if (response.contains("success")) { // Assuming a successful response contains "success"
                        navController.navigate("explore")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Log In", fontSize = 18.sp)
        }


        Spacer(modifier = Modifier.height(16.dp))


        // Mensaje de respuesta
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don´t have an account?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins,
                color = Color.Black,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(4.dp))
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
    }
}


private fun login(email: String, password: String, onResponse: (String) -> Unit) {
    val client = OkHttpClient()
    val json = """{"email": "$email", "password": "$password"}"""
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
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
