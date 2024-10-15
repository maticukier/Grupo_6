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
fun CheckoutScreen(

    navController: NavController,
    onDismissRequest: (() -> Unit)? = null
)

{
    val poppins = FontFamily(
        androidx.compose.ui.text.font.Font(R.font.poppins_regular),
    )
    // Cambia Dialog por Box para un control completo del layout
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color.Black.copy(alpha = 0.7f)) // Fondo semi-transparente
            .padding(0.dp) // Sin padding
    ) {
        // Tarjeta que ocupa todo el ancho
        Card(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .fillMaxHeight(0.55f) // Ocupa el 55% de la altura
                .align(Alignment.BottomCenter), // Colocado en la parte inferior
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    onClick = { navController.navigate("cart") },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
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
                    horizontalAlignment = Alignment.Start
                ) {
                    Column {
                        Text(
                            text = "Checkout",
                            fontSize = 22.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                        CheckoutMenuItem("Delivery", "Select Method")
                        CheckoutMenuItem("Payment", subtitleImage = painterResource(id = R.drawable.tarjeta))
                        CheckoutMenuItem("Promo Code", "Pick discount")
                        CheckoutMenuItem("Total Cost", "$13.97")
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "By placing an order you agree to our",
                                fontSize = 14.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7c7c7c)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Terms",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppins,
                                color = Color.Black,
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
                                fontFamily = poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7c7c7c)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Conditions",
                                fontSize = 14.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate("shop") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text("Place Order", fontSize = 18.sp, fontFamily = poppins,  fontWeight = FontWeight.ExtraBold,)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CheckoutMenuItem(title: String, subtitle: String? = null, subtitleImage: Painter? = null) {
    val poppins = FontFamily(
        androidx.compose.ui.text.font.Font(R.font.poppins_regular),
    )
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = poppins,
                color = Color(0xff7c7c7c),

                )
            Spacer(modifier = Modifier.weight(1f))
            if (subtitleImage != null) {
                Image(
                    painter = subtitleImage,
                    contentDescription = "Subtitle Image",
                    modifier = Modifier.padding(end = 8.dp)
                        .size(30.dp)
                )
            } else if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = poppins,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Go to $title",
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutScreen() {
    val navController = rememberNavController()

    CheckoutScreen(navController = navController)
}
