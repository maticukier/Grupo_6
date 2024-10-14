package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(navController: NavHostController, isDarkMode : Boolean){

    Box(modifier = Modifier.fillMaxSize()){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(title = "My Cart", isDarkMode = isDarkMode)
            Spacer(modifier = Modifier.height(8.dp))
            ProductDetail()
            Footer(navController = navController, selectedRoute = "shop", isDarkMode = isDarkMode,
                modifier = Modifier.align(Alignment.CenterHorizontally))    }
    }
}

@Composable
fun ProductDetail() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // Header con íconos de "back" y "share"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Volver */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Product Detail",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            IconButton(onClick = { /* Compartir */ }) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
        }

        // Imagen del producto
        Image(
            painter = painterResource(id = R.drawable.ginger), // Cambiar al recurso correcto
            contentDescription = "ginger",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Detalle del producto (nombre, cantidad, precio)
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Natural Red Apple", // Nombre del producto
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "1kg, Price", // Descripción del producto
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de cantidad
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Botón de restart
                Image(
                    painter = painterResource(id = R.drawable.minus), // Cambiar al recurso correcto
                    contentDescription = "Remove",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { /* Acción al reducir cantidad */ }
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Texto de la cantidad
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFF0F0F0),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = "1",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }


                Spacer(modifier = Modifier.width(20.dp))

                // Botón de sumar
                Image(
                    painter = painterResource(id = R.drawable.plus), // Cambiar al recurso correcto
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { /* Acción al reducir cantidad */ }
                        .padding(8.dp)
                )
            }
            Text(
                text = "$4.99",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Detail
        Column(modifier = Modifier.padding(horizontal = 16.dp)){
            Row(){
                Text(
                    text = "Product Detail",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(
                text = "Apples are nutritious. Apples may be good for weight loss. apples may be good for your heart. As part of a healtful and varied diet.",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nutritions
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nutritions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = R.drawable.nutritional), // Cambiar al recurso correcto
                contentDescription = "nutritional",
                modifier = Modifier
                    .size(50.dp) // Aumentar el tamaño total de la imagen
                    .padding(8.dp), // Agregar espacio alrededor si es necesario
                contentScale = ContentScale.FillWidth // Asegurar que la imagen se escale de manera adecuada
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Review
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Review",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = R.drawable.stars), // Cambiar al recurso correcto
                contentDescription = "stars",
                modifier = Modifier
                    .size(50.dp) // Aumentar el tamaño total de la imagen
                    .padding(8.dp), // Agregar espacio alrededor si es necesario
                contentScale = ContentScale.FillWidth // Asegurar que la imagen se escale de manera adecuada
            )
        }



        // Botón "Add to Basket"
        Button(
            onClick = { /* Acción para agregar al carrito */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Add To Basket",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
