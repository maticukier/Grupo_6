package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Explora los productos en una rejilla
@Composable
fun SearchScreen(navController: NavHostController, isDarkMode: Boolean) {
    var searchQuery by remember { mutableStateOf("") }

    // Filtrar la lista de productos de acuerdo a la búsqueda
    val filteredProducts = SearchList.filter { product ->
        product.name.contains(searchQuery, ignoreCase = true) ||
                product.description.contains(searchQuery, ignoreCase = true)
    }

    // Define los colores de fondo y texto según el modo oscuro
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Scaffold(
        bottomBar = { Footer(navController = navController, selectedRoute = "Explore", isDarkMode = isDarkMode) },
        containerColor = backgroundColor // Color de fondo del Scaffold
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor) // Color de fondo del Column
                .padding(innerPadding)
        ) {
            Header(title = "Search", isDarkMode = isDarkMode)
            Spacer(modifier = Modifier.height(8.dp))
            SearchProductBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { query -> searchQuery = query },
                navController = navController,
                isDarkMode = isDarkMode
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Pasar la lista filtrada a la grilla junto con los colores dinámicos
            SearchGrid(products = filteredProducts, cardBackgroundColor = backgroundColor, textColor = textColor)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchProductBar(searchQuery: String, onSearchQueryChange: (String) -> Unit, navController: NavHostController, isDarkMode: Boolean) {
    val backgroundColor = if (isDarkMode) Color.DarkGray else Color(0xFFF0F0F0)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val iconColor = if (isDarkMode) Color.White else Color.Black

    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { Text("Search store", color = textColor.copy(alpha = 0.6f)) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Search Icon",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            cursorColor = iconColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun SearchGrid(products: List<SearchProducts>, cardBackgroundColor: Color, textColor: Color) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(products) { _, product ->
            SearchCard(
                product = product,
                cardBackgroundColor = cardBackgroundColor,
                textColor = textColor
            )
        }
    }
}

@Composable
fun SearchCard(product: SearchProducts, cardBackgroundColor: Color, textColor: Color) {
    Card(
        modifier = Modifier
            .width(173.32.dp)
            .height(248.51.dp)
            .padding(8.dp)
            .border(1.dp, if (cardBackgroundColor == Color.Black) Color.White else Color.LightGray, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor // Color dinámico de la tarjeta
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
                Text(text = product.description, fontSize = 12.sp, color = if (cardBackgroundColor == Color.Black) Color.LightGray else Color.Gray)
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
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController(), isDarkMode = false)
}
@Preview(showBackground = true)
@Composable
fun SearchScreenDarkModePreview() {
    SearchScreen(navController = rememberNavController(), isDarkMode = true)
}

// Clase de datos y listas
data class SearchProducts(val name: String, val description: String, val price: String, val imageResId: Int)

val SearchList = listOf(
    SearchProducts("Organic Bananas", "7pcs, Priceg", "$4.99", R.drawable.banana),
    SearchProducts("Red Apple", "1kg, Priceg", "$4.99", R.drawable.apple),
    SearchProducts("Bell Pepper Red", "1kg, Priceg", "$4.99", R.drawable.tomato),
    SearchProducts("Ginger", "250mg, Priceg", "$2.99", R.drawable.ginger),
    SearchProducts("Sprite Can", "325ml, Price", "$1.50", R.drawable.sprite),
    SearchProducts("Diet Coke Can", "355ml, Price", "$1.99", R.drawable.coca_light),
    SearchProducts("Apple & Grape Juice", "2l, Price", "$15.50", R.drawable.jugo),
    SearchProducts("Coca Cola Can", "325ml, Price", "$4.99", R.drawable.coca),
    SearchProducts("Pepsi Can", "330ml, Price", "$4.99", R.drawable.pepsi),
    SearchProducts("Egg Chicken Red", "4pcs, Price", "$1.99", R.drawable.eggred),
    SearchProducts("Egg Chicken White", "180g, Price", "$1.50", R.drawable.eggwhite),
    SearchProducts("Egg Pasta", "30gm, Price", "$15.99", R.drawable.eggpasta),
    SearchProducts("Egg Noodles", "2L, Price", "$15.99", R.drawable.eggnoodles),

)




