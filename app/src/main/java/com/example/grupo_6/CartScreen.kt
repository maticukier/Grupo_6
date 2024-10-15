package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun CartScreen(navController: NavHostController, isDarkMode: Boolean) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)

    var showCheckoutDialog by remember { mutableStateOf(false) }
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    val total = calculateTotal(ProductsInCart)

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 150.dp) // Espacio para el botón y el Footer
                ) {
                    item {
                        Header(title = "My Cart", isDarkMode = isDarkMode)
                        Spacer(modifier = Modifier.height(2.dp))
                    }

                    items(ProductsInCart) { product ->
                        ProductInCart(productCart = product, isDarkMode = isDarkMode)
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp)) // Added spacer to increase separation
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = {
                            showCheckoutDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash)),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(1.5f)) // Adjusted weight to push text slightly to the right
                            Text(
                                "Go to Checkout",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            )
                            Spacer(modifier = Modifier.weight(0.8f)) // Adjusted weight to balance the layout
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF489E67), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("$$total", color = Color.White)
                            }
                        }
                    }

                    Footer(
                        navController = navController,
                        selectedRoute = "cart",
                        isDarkMode = isDarkMode,
                        modifier = Modifier.fillMaxWidth() // Alineación del Footer al fondo
                    )
                }
            }
        }
    )

    if (showCheckoutDialog) {
        CheckoutScreen(
            navController = navController,
            onDismissRequest = { showCheckoutDialog = false }
        )
    }
}
fun calculateTotal(products: List<CartProduct>): Double {
    return products.sumOf { product ->
        product.price.removePrefix("$").toDouble() * product.quantity
    }
}
@Composable
fun ProductInCart(productCart: CartProduct, textColor: Color, textStyle: TextStyle) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = productCart.imageResId),
            contentDescription = productCart.name,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 16.dp)
        )

        Column {
            Text(text = productCart.name, color = textColor, style = textStyle)
            Text(text = productCart.description, color = Color.Gray, style = textStyle)
            Text(text = productCart.price, color = textColor, style = textStyle)
        }
    }
}

// Estructura de datos para los productos
data class CartProduct(val name: String, val description: String, val price: String, val imageResId: Int,  var quantity: Int=1) {

}

// Lista de productos en el carrito (ejemplo)
val ProductsInCart = listOf(
    CartProduct("Bell Pepper Red", "1kg, Price", "$4.99", R.drawable.morron, 1),
    CartProduct("Egg Chicken Red", "4pcs, Price", "$1.99", R.drawable.egg, 1),
    CartProduct("Organic Bananas", "12kg, Price", "$3.00", R.drawable.banana, 1),
    CartProduct("Ginger", "250mg, Price", "$2.99", R.drawable.ginger, 1),
    CartProduct("Sprite Can", "325ml, Price", "$1.50", R.drawable.sprite, 1),
)
@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController(), isDarkMode = false) // Vista en modo claro
}

@Preview(showBackground = true)
@Composable
fun CartScreenDarkModePreview() {
    CartScreen(navController = rememberNavController(), isDarkMode = true) // Vista en modo oscuro
}
