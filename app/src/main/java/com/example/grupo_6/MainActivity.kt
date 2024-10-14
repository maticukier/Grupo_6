package com.example.grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grupo_6.ui.theme.Grupo_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            AppNavigation(isDarkMode = isDarkMode, toggleDarkMode = { isDarkMode = it })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(isDarkMode: Boolean, toggleDarkMode: (Boolean) -> Unit) {
    val navController = rememberNavController()

    Grupo_6Theme(isDarkTheme = isDarkMode) {
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen()

                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000)  // 2 segundos de espera
                    navController.navigate("prelogin") {
                        popUpTo("splash") { inclusive = true } // Eliminar Splash de la pila
                    }
                }
            }

            composable("prelogin") { PreLoginScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("location") { LocationScreen(navController) }
            composable("shop") {
                HomeScreen(navController = navController, isDarkMode = isDarkMode)
            }
            composable("cart") { CartScreen(navController , isDarkMode = isDarkMode) }
            composable("explore") {
                ExploreScreen(navController = navController, isDarkMode = isDarkMode)
            }
            composable("productDetail"){ ProductDetailScreen(navController, isDarkMode = isDarkMode) }

            composable("favourite") {
                FavouriteScreen(navController = navController, isDarkMode = isDarkMode)
            }
            composable("account") {
                AccountScreen(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    toggleDarkMode = toggleDarkMode
                )
            }
        }
    }
}



