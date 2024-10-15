package com.example.grupo_6

import android.view.LayoutInflater
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController


@Composable
fun PreLoginScreen(navController: NavHostController) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.prelogin, null)
        },
        update = { view ->
            val button = view.findViewById<Button>(R.id.button)
            button.setOnClickListener {
                navController.navigate("login")
            }
        }
    )
}
