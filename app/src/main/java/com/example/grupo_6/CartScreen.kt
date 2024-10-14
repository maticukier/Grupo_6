package com.example.grupo_6

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CartScreen(navController: NavHostController){

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(title = "My Cart")
            Spacer(modifier = Modifier.height(8.dp))
            ProductsInCart.forEach { product ->
                ProductInCart(productCart = product)
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
                Text("Go to Checkout", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }        }
        Footer(navController = navController, selectedRoute = "shop", modifier = Modifier.align(Alignment.BottomCenter))
    }
}

val ProductsInCart = listOf(
    ProductCart("Bell Pepper Red", "1kg, Price", "$4.99", R.drawable.morron),
    ProductCart("Egg Chicken Red", "4pcs, Price", "$1.99", R.drawable.egg),
    ProductCart("Organic Bananas", "12kg, Price", "$3.00", R.drawable.banana),
    ProductCart("Ginger", "250gm, Price", "$2.99", R.drawable.ginger),
)
