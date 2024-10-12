package com.example.grupo_6

import androidx.compose.foundation.Image
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip

@Composable
fun FavouriteScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Favourites")

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(FavouriteProducts) { product ->
                FavouriteProductItem(product)
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }
        }

        Button(
            onClick = { /* Handle 'Add All To Cart' */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Add All To Cart", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Footer(navController = navController, selectedRoute = "favourite", modifier = Modifier.align(Alignment.CenterHorizontally))    }
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
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(text = product.name, fontWeight = FontWeight.Bold)
            Text(text = product.description, color = Color.Gray)
        }
        Text(text = product.price, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(id = R.drawable.flecha), // Replace with your PNG button resource
            contentDescription = "Flecha",
            modifier = Modifier
                .padding(8.dp)
                .clickable { /* Handle button click */ }
        )

    }
}

@Composable
fun FavouriteProductList(products: List<FavouriteProduct>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(products.chunked(2)) { index, productPair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                productPair.forEach { product ->
                    FavouriteProductCard(product = product)
                }
            }
        }
    }
}

@Composable
fun FavouriteProductCard(product: FavouriteProduct) {
    Card(
        modifier = Modifier
            .width(173.32.dp)
            .height(248.51.dp)
            .padding(8.dp) // Apply positive padding
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)), // Add rounded border with light grey color and thin lines
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Set the background color to white
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
                Text(text = product.name, fontSize = 16.sp, color = Color.Black)
                Text(text = product.description, fontSize = 12.sp, color = Color.Gray)
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(text = product.price, fontSize = 16.sp, color = Color.Black)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(40.dp) // Adjust the size as needed
            )
        }
    }
}

data class FavouriteProduct(val name: String, val description: String, val price: String, val imageResId: Int)

// Separate lists for Exclusive Offer and Best Sellings products
val FavouriteProducts = listOf(
    Product("Sprite Can", "325ml , Price", "$1.50", R.drawable.sprite),
    Product("Diet Coke Can", "355ml , Price", "$1.99", R.drawable.coca_light),
    Product("Apple & Grape Juice ", "2l ,", "$15.50", R.drawable.jugo),
    Product("Coca Cola Can", "325ml , Price", "$4.99", R.drawable.coca),
    Product("Pepsi Can", "330ml , Price", "$4.99", R.drawable.pepsi),
)

@Preview(showBackground = true)
@Composable
fun FavouriteScreenPreview() {
    FavouriteScreen(navController = rememberNavController())
}