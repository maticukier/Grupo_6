package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavouriteScreen(navController: NavHostController, isDarkMode: Boolean) {
    Scaffold(
        bottomBar = { Footer(navController = navController, selectedRoute = "favourite", isDarkMode = isDarkMode) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // El Header ahora acepta el estado de modo oscuro
            Header(title = "Favourites", isDarkMode = isDarkMode)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(FavouriteProducts) { product ->
                    FavouriteProductItem(product)
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }
            Button(
                onClick = { /* Add all to cart */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text("Add All To Cart", color = Color.White)
            }
        }
    }
}



@Composable
fun FavouriteProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier.size(60.dp)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = product.name, fontWeight = FontWeight.Bold)
            Text(text = product.description, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = product.price, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteScreenPreview() {
    FavouriteScreen(navController = rememberNavController(), isDarkMode = false)
}

// Lista de productos favoritos
val FavouriteProducts = listOf(
    Product("Sprite Can", "325ml, Price", "$1.50", R.drawable.sprite),
    Product("Diet Coke Can", "355ml, Price", "$1.99", R.drawable.coca_light),
    Product("Apple & Grape Juice", "2l, Price", "$15.50", R.drawable.jugo),
    Product("Coca Cola Can", "325ml, Price", "$4.99", R.drawable.coca),
    Product("Pepsi Can", "330ml, Price", "$4.99", R.drawable.pepsi)
)
