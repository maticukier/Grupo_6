package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductsScreen(navController: NavHostController, categoryName: String, isDarkMode: Boolean) {
    val products = getProductsByCategory(categoryName)
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(categoryName)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor
                )
            )
        },
        content = { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(products) { product ->
                    ProductCard(product = product, cardBackgroundColor = backgroundColor, textColor = textColor)
                }
            }
        }
    )
}

fun getProductsByCategory(categoryName: String): List<Product> {
    return when (categoryName) {
        "Beverages" -> listOf(
            Product("Diet Coca", "335Ml, Priceg", "$1.99", R.drawable.coca_light),
            Product("Sprite", "1L, Priceg", "$1.50", R.drawable.sprite),
            Product("Apple & Grape Juice", "2L, Priceg", "$15.99", R.drawable.jugo),
            Product("Orange Juice", "2L, Priceg", "$15.99", R.drawable.orange),
            Product("Coca Cola Can", "335Ml, Priceg", "$4.99", R.drawable.coca),
            Product("Pepsi", "335Ml, Priceg", "$4.99", R.drawable.pepsi),

        )
        "Fresh Fruits & Vegetable" -> listOf(
            Product("Organic Bananas", "7pcs, Priceg", "$4.99", R.drawable.banana),
            Product("Red Apple", "1kg, Priceg", "$4.99", R.drawable.apple),
            Product("Bell Pepper Red", "1kg, Priceg", "$4.99", R.drawable.tomato),
            Product("Ginger", "250mg, Priceg", "$2.99", R.drawable.ginger)
        )

        "Dairy & Eggs" -> listOf(
            Product("Milk", "1L, Priceg", "$1.99", R.drawable.milk),
            Product("Eggs", "180gm, Priceg", "$2.99", R.drawable.onion),
            Product("Egg Pasta", "30mg, Priceg", "$15.99", R.drawable.eggpasta),
                    Product("Egg Noddles", "30mg, Priceg", "$15.99", R.drawable.eggnoddles)



        )
        "Bakery & Snacks" -> listOf(
            Product("Bread", "1 loaf, Priceg", "$3.99", R.drawable.bread),
            Product("Chocolate Chip Cookies", "200g, Priceg", "$2.99", R.drawable.cookie),
        )

        "Cooking oil & ghee" -> listOf(
            Product("Olive Oil", "1L, Priceg", "$9.99", R.drawable.naturaloil),
            Product("Ghee", "500g, Priceg", "$6.99", R.drawable.ghee)
        )

        "Meat & Fish" -> listOf(
            Product("Chicken", "1kg, Priceg", "$12.99", R.drawable.chicken),
            Product("Salmon", "1kg, Priceg", "$19.99", R.drawable.salmon),
            Product("Beef Steak", "1kg, Priceg", "$15.99", R.drawable.beefsteak)
        )
        else -> emptyList()
    }
}

@Composable
fun ProductCard(product: Product, cardBackgroundColor: Color, textColor: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(220.dp) // Increased height to ensure all elements fit
            .border(1.dp, textColor, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(), // Ensure the column takes up the full height
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween // Space elements evenly
            ) {
                Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // Adjusted height for the image
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.name, fontSize = 16.sp, color = textColor, fontWeight = FontWeight.Bold)
                Text(text = product.description, fontSize = 12.sp, color = Color.Gray) // Added subtitle
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = product.price, fontSize = 14.sp, color = textColor)
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

@Preview(showBackground = true)
@Composable
fun CategoryProductsScreenPreview() {
    CategoryProductsScreen(navController = rememberNavController(), categoryName = "Beverages", isDarkMode = false)
}