package com.example.grupo_6

import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OrderAcceptedScreen(
    navController: NavController,
    onDismissRequest: (() -> Unit)? = null
) {
    val poppins = FontFamily(
        androidx.compose.ui.text.font.Font(R.font.poppins_regular),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fondoorder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.orderaccepted),
                        contentDescription = "Bolsa de Alimentos",
                        modifier = Modifier
                            .size(260.dp)
                            .offset(y = 40.dp) // Move the Image slightly down
                    )
                    Spacer(modifier = Modifier.height(90.dp))
                    Text(
                        text = "Your Order has been accepted",
                        fontSize = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center, // Alinea el texto al centro
                        modifier = Modifier.fillMaxWidth() // Hace que el texto ocupe todo el ancho disponible
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Your items has been placed and is on it´s way to being processed",
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff7c7c7c),
                        textAlign = TextAlign.Center, // Alinea el texto al centro
                        modifier = Modifier.fillMaxWidth() // Hace que el texto ocupe todo el ancho disponible
                    )

                    Spacer(modifier = Modifier.height(110.dp))
                    Button(
                        onClick = { navController.navigate("cart") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("Track Order", fontSize = 18.sp, fontFamily = poppins, fontWeight = FontWeight.ExtraBold)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navController.navigate("shop") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
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
fun PreviewOrderAcceptedScreen() {
    val navController = rememberNavController()

    OrderAcceptedScreen(navController = navController)
}