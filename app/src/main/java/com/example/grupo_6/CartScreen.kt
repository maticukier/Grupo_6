package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun CartScreen(navController: NavHostController, isDarkMode: Boolean) {
    // Estado para controlar si el CheckoutScreen se debe mostrar
    var showCheckoutDialog by remember { mutableStateOf(false) }

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(title = "My Cart", isDarkMode = isDarkMode)
            Spacer(modifier = Modifier.height(8.dp))

            ProductsInCart.forEach { product ->
                ProductInCart(productCart = product, textColor = textColor)
            }

            // Botón para ir al Checkout
            Button(
                onClick = { showCheckoutDialog = true }, // Al presionar, se muestra el dialogo
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text("Go to Checkout", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Mostrar el diálogo de Checkout cuando showCheckoutDialog es true
        if (showCheckoutDialog) {
            CheckoutScreen(
                navController = navController,
                onDismissRequest = { showCheckoutDialog = false } // Cerrar el dialogo
            )
        }

        // Solo mostrar el Footer si el CheckoutScreen no está visible
        if (!showCheckoutDialog) {
            Footer(
                navController = navController,
                selectedRoute = "cart",
                isDarkMode = isDarkMode,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}


@Composable
fun ProductInCart(productCart: CartProduct, textColor: Color) {
    // Diseño de cada producto en el carrito
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically // Alineación de los elementos en la fila
    ) {
        // Imagen del producto
        Image(
            painter = painterResource(id = productCart.imageResId),
            contentDescription = productCart.name,
            modifier = Modifier
                .size(64.dp) // Tamaño de la imagen
                .padding(end = 16.dp) // Espaciado con el resto del contenido
        )

        // Columna con la información del producto
        Column {
            Text(text = productCart.name, color = textColor)
            Text(text = productCart.description, color = Color.Gray)
            Text(text = productCart.price, color = textColor)
        }
    }
}


// Estructura de datos para los productos
data class CartProduct(val name: String, val description: String, val price: String, val imageResId: Int)

// Lista de productos en el carrito (ejemplo)
val ProductsInCart = listOf(
    CartProduct("Bell Pepper Red", "1kg, Price", "$4.99", R.drawable.morron),
    CartProduct("Egg Chicken Red", "4pcs, Price", "$1.99", R.drawable.egg),
    CartProduct("Organic Bananas", "12kg, Price", "$3.00", R.drawable.banana),
    CartProduct("Ginger", "250gm, Price", "$2.99", R.drawable.ginger),
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
