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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class para representar un producto en el carrito
@Composable
fun ProductInCart(productCart: CartProduct, isDarkMode: Boolean) {
    // Usar un estado local para la cantidad
    var quantity by remember { mutableStateOf(productCart.quantity) }

    // Definir los colores para modo claro y oscuro
    val cardBackgroundColor = if (isDarkMode) Color.DarkGray else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val descriptionColor = if (isDarkMode) Color.LightGray else Color.Gray
    val borderColor = if (isDarkMode) Color.Gray else Color.LightGray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 3.dp)
            .border(1.dp, borderColor), // Cambiar color del borde según el modo
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor // Cambiar el fondo según el modo
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
                painter = painterResource(id = productCart.imageResId),
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
                    text = productCart.name,
                    fontSize = 18.sp,
                    color = textColor, // Cambiar color del texto según el modo
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = productCart.description,
                    fontSize = 14.sp,
                    color = descriptionColor // Cambiar color de la descripción
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
                            .clickable {
                                if (quantity > 1) {
                                    quantity--
                                }
                            } // Disminuir cantidad
                            .padding(12.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFF0F0F0),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = quantity.toString(), // Mostrar la cantidad actual
                        fontSize = 16.sp,
                        color = textColor // Cambiar color del texto según el modo
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Image(
                        painter = painterResource(id = R.drawable.plus), // Reemplazar con el recurso adecuado
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(64.dp)
                            .clickable { quantity++ } // Aumentar cantidad
                            .padding(12.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFF0F0F0),
                                shape = RoundedCornerShape(8.dp)
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
                    text = productCart.price,
                    fontSize = 16.sp,
                    color = textColor, // Cambiar color del texto según el modo
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
