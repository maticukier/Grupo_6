package com.example.grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        // Pantalla de Splash
        composable("splash") {
            SplashScreen()

            // Simular una espera antes de navegar a PreLoginScreen
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(2000)  // 2 segundos de espera
                navController.navigate("prelogin") {
                    popUpTo("splash") { inclusive = true } // Eliminar Splash de la pila
                }
            }
        }

        // Pantalla PreLogin
        composable("prelogin") {
            PreLoginScreen(navController)
        }

        // Pantalla de Login
        composable("login") {
            LoginScreen(navController)
        }

        // Pantalla de Sign Up
        composable("signup") { SignUpScreen() }

        composable("shop") { HomeScreen(navController) }
        composable("explore") { ExploreScreen(navController) }
        //composable("cart") { CartScreen(navController) }
        //composable("favorite") { FavoriteScreen(navController) }
        //composable("account") { AccountScreen(navController) }
    }
}


