package com.example.grupo_6

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme


@Composable
fun ErrorScreen(
    navController: NavController,
    onDismissRequest: (() -> Unit)? = null
) {
    val poppins = FontFamily(
        androidx.compose.ui.text.font.Font(R.font.poppins_regular),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(0.dp)
    ) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(600.dp)
                .align(Alignment.Center)
                .offset(y = 50.dp), // Move the Card slightly down
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    onClick = { navController.navigate("cart") },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close",
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bolsaalimentos),
                        contentDescription = "Bolsa de Alimentos",
                        modifier = Modifier
                            .size(200.dp)
                            .offset(y = 40.dp) // Move the Image slightly down
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Oops! Order Failed",
                        fontSize = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Text(
                        text = "Something went terribly wrong.",
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff7c7c7c)
                    )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate("shop") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text("Please try again", fontSize = 18.sp, fontFamily = poppins, fontWeight = FontWeight.ExtraBold)
                        }
                        Button(
                            onClick = { navController.navigate("shop") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                "Back to home",
                                fontSize = 18.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black // Set text color to black
                            )
                        }
                    }
                }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() {
    val navController = rememberNavController()

    ErrorScreen(navController = navController)
}
