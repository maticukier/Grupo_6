package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Explora los productos en una rejilla
@Composable
fun ExploreScreen(navController: NavHostController, isDarkMode: Boolean) {
    Scaffold(
        bottomBar = { Footer(navController = navController, selectedRoute = "Explore", isDarkMode = isDarkMode) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Pasamos isDarkMode al Header
            Header(title = "Explore", isDarkMode = isDarkMode)
            Spacer(modifier = Modifier.height(8.dp))
            ExploreGrid()
        }
    }
}

@Composable
fun ExploreGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categoryList) { index, category ->
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
    Category("Frash Fruits & Vegetable", R.drawable.freshfruits),
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
