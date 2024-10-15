package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class para representar un producto en el carrito
data class ProductCart(val name: String, val description: String, val price: String, val imageResId: Int)

@Composable
fun ProductInCart(productCart: CartProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .border(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = productCart.imageResId), // Usar el recurso de imagen proporcionado
                contentDescription = productCart.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            // Nombre y detalles del producto
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = productCart.name, // Nombre del producto
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold // Hace el texto más grueso (bold)
                )
                Text(
                    text = productCart.description, // Descripción del producto
                    fontSize = 14.sp,
                    color = Color.Gray // Cambia el color a gris
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.minus), // Reemplazar con el recurso adecuado
                        contentDescription = "Remove",
                        modifier = Modifier
                            .size(64.dp)
                            .clickable { /* Acción al reducir cantidad */ }
                            .padding(12.dp) // Espacio entre la imagen y el borde
                            .border(
                                width = 1.dp,
                                color = Color(0xFFF0F0F0),
                                shape = RoundedCornerShape(8.dp) // Bordes redondeados
                            )
                            .padding(12.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "1", // Cantidad actual (esto podría ser un valor dinámico si fuera necesario)
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Image(
                        painter = painterResource(id = R.drawable.plus), // Reemplazar con el recurso adecuado
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(64.dp)
                            .clickable { /* Acción al aumentar cantidad */ }
                            .padding(12.dp) // Espacio entre la imagen y el borde
                            .border(
                                width = 1.dp,
                                color = Color(0xFFF0F0F0),
                                shape = RoundedCornerShape(8.dp) // Bordes redondeados
                            )
                            .padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Precio y botón de eliminar
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close), // Reemplazar con el recurso adecuado
                    contentDescription = "Remove Item",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Acción al eliminar producto */ }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = productCart.price, // Precio del producto
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold // Hace el texto más grueso (bold)
                )
            }
        }
    }
}
