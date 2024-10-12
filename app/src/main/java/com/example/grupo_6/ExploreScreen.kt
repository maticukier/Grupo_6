package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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

@Composable
fun ExploreScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(title = "Explore")
            Spacer(modifier = Modifier.height(8.dp))
            ExploreGrid()
            Spacer(modifier = Modifier.weight(1f))
        }
        Footer(navController = navController, selectedRoute = "explore", modifier = Modifier.align(Alignment.BottomCenter))
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
            Text(text = category.name, fontSize = 16.sp, color = Color.Black)
        }
    }
}

data class Category(val name: String, val imageResId: Int)

// Sample list of categories for the Explore screen
val categoryList = listOf(
    Category("Fresh fruits and vegetables", R.drawable.freshfruits),
    Category("Cooking oil & ghee", R.drawable.oil),
    Category("Meat & Fish", R.drawable.meat),
    Category("Bakery & Snacks", R.drawable.snacks),
    Category("Dairy & Eggs", R.drawable.milk),
    Category("Beverages", R.drawable.bebidas)
)

// List of varied colors
val variedColors = listOf(
    Color(0xFFE8F5E9),
    Color(0xFFFFE0B2),
    Color(0xFFFFCDD2),
    Color(0xFFD7BDE2),
    Color(0xFFFFF9C4),
    Color(0xFFB2EBF2)
)

// Extension function to darken a color
fun Color.darken(factor: Float): Color {
    return Color(
        red = (red * (1 - factor)).coerceIn(0f, 1f),
        green = (green * (1 - factor)).coerceIn(0f, 1f),
        blue = (blue * (1 - factor)).coerceIn(0f, 1f),
        alpha = alpha
    )
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(navController = rememberNavController())
}