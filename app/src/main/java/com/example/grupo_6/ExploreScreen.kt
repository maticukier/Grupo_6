package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@Composable
fun ExploreScreen(navController: NavHostController, isDarkMode: Boolean) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCategories = categoryList.filter { it.name.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        bottomBar = { Footer(navController = navController, selectedRoute = "explore", isDarkMode = isDarkMode,) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header(title = "Explore", isDarkMode = isDarkMode)
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar(
                searchQuery,
                onSearchQueryChange = { query -> searchQuery = query },
                onTrailingIconClick = {
                    navController.navigate("filters")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExploreGrid(filteredCategories)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: String, onSearchQueryChange: (String) -> Unit, onTrailingIconClick: () -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = { Text("Search store") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Search Icon",
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onTrailingIconClick()
                }.size(24.dp),
                painter = painterResource(id = R.drawable.ajustes),
                contentDescription = "Adjust Icon",
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF0F0F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun ExploreGrid(categories: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            val backgroundColor = variedColors[index % variedColors.size]
            val borderColor = backgroundColor.darken(0.2f)
            ExploreCard(category = category, backgroundColor = backgroundColor, borderColor = borderColor)
        }
    }
}

@Composable
fun ExploreCard(category: Category, backgroundColor: Color, borderColor: Color) {
    Card(
        modifier = Modifier
            .width(174.5.dp)
            .height(189.11.dp)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = category.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category.name, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(navController = rememberNavController(), isDarkMode = false)
}

// Clase de datos y listas
data class Category(val name: String, val imageResId: Int)

val categoryList = listOf(
    Category("Fresh Fruits & Vegetable", R.drawable.freshfruits),
    Category("Cooking oil & ghee", R.drawable.oil),
    Category("Meat & Fish", R.drawable.meat),
    Category("Bakery & Snacks", R.drawable.snacks),
    Category("Dairy & Eggs", R.drawable.milk),
    Category("Beverages", R.drawable.bebidas)
)

val variedColors = listOf(
    Color(0xFFE8F5E9),
    Color(0xFFFFE0B2),
    Color(0xFFFFCDD2),
    Color(0xFFD7BDE2),
    Color(0xFFFFF9C4),
    Color(0xFFB2EBF2)
)

// Función de extensión para oscurecer un color
fun Color.darken(factor: Float): Color {
    return Color(
        red = (red * (1 - factor)).coerceIn(0f, 1f),
        green = (green * (1 - factor)).coerceIn(0f, 1f),
        blue = (blue * (1 - factor)).coerceIn(0f, 1f),
        alpha = alpha
    )
}