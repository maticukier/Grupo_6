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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.TextStyle


@Composable
fun FavouriteScreen(navController: NavHostController, isDarkMode: Boolean) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)
    val showErrorScreen = remember { mutableStateOf(false) }

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Header(title = "Favourites", isDarkMode = isDarkMode)
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                    LazyColumn(
                        modifier = Modifier
                            .weight(12f) // Ensure LazyColumn takes available space
                            .padding(16.dp)
                    ) {
                        items(FavouriteProducts) { product ->
                            FavouriteProductItem(product, textStyle)
                            HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = { showErrorScreen.value = true },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.9f)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text(
                            "Add All To Cart",
                            fontSize = 18.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.ExtraBold,
                            style = textStyle
                        )
                    }

                    Footer(
                        navController = navController,
                        selectedRoute = "favourite",
                        isDarkMode = isDarkMode,
                        modifier = Modifier.fillMaxWidth() // Align Footer to the bottom
                    )
                }
            }
        }
    )

    if (showErrorScreen.value) {
        ErrorScreen(navController = navController, onDismissRequest = { showErrorScreen.value = false })
    }
}

@Composable
fun FavouriteProductItem(product: Product, textStyle: TextStyle) {
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
            Text(text = product.name, fontWeight = FontWeight.Bold, style = textStyle)
            Text(text = product.description, color = Color.Gray, style = textStyle)
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = product.price, fontWeight = FontWeight.Bold, style = textStyle)
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Arrow",
                modifier = Modifier.size(18.dp).padding(start = 10.dp)
            )
        }
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
