package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController, isDarkMode: Boolean) {
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // Fondo dinámico según el modo oscuro
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(title = "Shop", isDarkMode = isDarkMode) // Pasamos isDarkMode al Header
            Spacer(modifier = Modifier.height(8.dp))
            CityTitle(cityName = "Dhaka, Banasree", textColor = textColor) // Color dinámico para el título de la ciudad
            BannerImage()
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(title = "Exclusive Offer", actionTitle = "See All", textColor = textColor) // Color dinámico para la sección
            ProductList(products = exclusiveOfferProducts, isDarkMode = isDarkMode, navController = navController) // Color dinámico para los productos
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(title = "Best Sellings", actionTitle = "See All", textColor = textColor)
            ProductList(products = bestSellingProducts, isDarkMode = isDarkMode, navController = navController)
            Spacer(modifier = Modifier.weight(1f))
        }
        Footer(navController = navController, selectedRoute = "shop", isDarkMode = isDarkMode, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun CityTitle(cityName: String, textColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cityName,
            fontSize = 18.sp,
            color = textColor // Color dinámico
        )
    }
}
@Composable
fun BannerImage() {
    Image(
        painter = painterResource(id = R.drawable.banner),
        contentDescription = "Banner Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Adjust the height as needed
    )
}
@Composable
fun SectionTitle(title: String, actionTitle: String, textColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            color = textColor // Color dinámico
        )
        Text(
            text = actionTitle,
            fontSize = 14.sp,
            color = textColor // Color dinámico
        )
    }
}

@Composable
fun ProductList(products: List<Product>, isDarkMode: Boolean, navController: NavHostController) {
    val cardBackgroundColor = if (isDarkMode) Color.DarkGray else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(products.chunked(2)) { index, productPair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                productPair.forEach { product ->
                    ProductCard(product = product, cardBackgroundColor = cardBackgroundColor, textColor = textColor, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, cardBackgroundColor: Color, textColor: Color, navController : NavHostController) {
    Card(
        modifier = Modifier
            .width(173.32.dp)
            .height(248.51.dp)
            .padding(8.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("productDetail")
            },
            colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor // Color dinámico de la tarjeta.png
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.name, fontSize = 16.sp, color = textColor) // Texto dinámico
                Text(text = product.description, fontSize = 12.sp, color = Color.Gray)
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(text = product.price, fontSize = 16.sp, color = textColor) // Texto dinámico
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add Button",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { /* Handle button click */ }
                )
            }
        }
    }
}

data class Product(val name: String, val description: String, val price: String, val imageResId: Int)

// Separate lists for Exclusive Offer and Best Sellings products
val exclusiveOfferProducts = listOf(
    Product("Organic Bananas", "7pcs, Priceg", "$4.99", R.drawable.banana),
    Product("Red Apple", "1kg, Priceg", "$4.99", R.drawable.apple)
)

val bestSellingProducts = listOf(
    Product("Bell Pepper Red", "1kg, Priceg", "$4.99", R.drawable.tomato),
    Product("Ginger", "250mg, Priceg", "$2.99", R.drawable.ginger)
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), isDarkMode = false)
}