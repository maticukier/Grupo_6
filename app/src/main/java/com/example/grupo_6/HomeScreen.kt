package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen(navController: NavHostController, isDarkMode: Boolean) {
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Header(title = "Shop", isDarkMode = isDarkMode)
                Spacer(modifier = Modifier.height(8.dp))
                CityTitle(cityName = "Dhaka, Banasree", textColor = textColor, textStyle = textStyle)
                BannerImage()
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Exclusive Offer", actionTitle = "See All", textColor = textColor, textStyle = textStyle)
                ProductList(products = exclusiveOfferProducts, isDarkMode = isDarkMode, navController = navController, textStyle = textStyle)
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Best Sellings", actionTitle = "See All", textColor = textColor, textStyle = textStyle)
                ProductList(products = bestSellingProducts, isDarkMode = isDarkMode, navController = navController, textStyle = textStyle)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Footer(navController = navController, selectedRoute = "shop", isDarkMode = isDarkMode, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun CityTitle(cityName: String, textColor: Color, textStyle: TextStyle) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cityName,
            fontSize = 18.sp,
            color = textColor,
            style = textStyle
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
            .height(200.dp)
    )
}

@Composable
fun SectionTitle(title: String, actionTitle: String, textColor: Color, textStyle: TextStyle) {
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
            color = textColor,
            style = textStyle
        )
        Text(
            text = actionTitle,
            fontSize = 14.sp,
            color = textColor,
            style = textStyle
        )
    }
}

@Composable
fun ProductList(products: List<Product>, isDarkMode: Boolean, navController: NavHostController, textStyle: TextStyle) {
    val cardBackgroundColor = if (isDarkMode) Color.DarkGray else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(products.chunked(2)) { _, productPair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                productPair.forEach { product ->
                    ProductCard(product = product, cardBackgroundColor = cardBackgroundColor, textColor = textColor, navController = navController, textStyle = textStyle)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, cardBackgroundColor: Color, textColor: Color, navController: NavHostController, textStyle: TextStyle) {
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
            containerColor = cardBackgroundColor
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
                Text(text = product.name, fontSize = 16.sp, color = textColor, style = textStyle)
                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = textStyle
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(text = product.price, fontSize = 16.sp, color = textColor, style = textStyle)
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

val exclusiveOfferProducts = listOf(
    Product("Organic Bananas", "7pcs, Priceg", "$4.99", R.drawable.banana),
    Product("Red Apple", "1kg, Priceg", "$4.99", R.drawable.apple),
    Product("Coca", "1L, Priceg", "$4.99", R.drawable.coca)
)

val bestSellingProducts = listOf(
    Product("Bell Pepper Red", "1kg, Priceg", "$4.99", R.drawable.tomato),
    Product("Ginger", "250mg, Priceg", "$2.99", R.drawable.ginger),
    Product("Pepsi", "250mg, Priceg", "$4.99", R.drawable.pepsi)
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), isDarkMode = false)
}