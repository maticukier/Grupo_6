package com.example.grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.grupo_6.ui.theme.Grupo_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //aca pongan la clase que quieren probar en funcionamiento
            LocationScreen()
            }
        }
    }

@Preview
@Composable
fun DecoratedComposablePreview() {
    //acá pongan la clase que quieran hacerle preview
    LocationScreen()
}

