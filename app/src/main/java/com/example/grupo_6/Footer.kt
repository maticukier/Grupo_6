package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Footer(navController: NavHostController, selectedRoute: String, isDarkMode: Boolean, modifier: Modifier = Modifier) {
    var selectedRouteState by remember { mutableStateOf(selectedRoute) }

    val backgroundColor = if (isDarkMode) Color(0xFF1E1E1E) else Color(0xFFFCFCFC)
    val textColor = if (isDarkMode) Color.White else Color.Black

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
            .zIndex(15f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FooterItem(
            iconResId = R.drawable.shop,
            label = "Shop",
            isSelected = selectedRouteState == "shop",
            textColor = textColor
        ) {
            selectedRouteState = "shop"
            navController.navigate("shop")
        }
        FooterItem(
            iconResId = R.drawable.search,
            label = "Explore",
            isSelected = selectedRouteState == "explore",
            textColor = textColor
        ) {
            selectedRouteState = "explore"
            navController.navigate("explore")
        }
        FooterItem(
            iconResId = R.drawable.cart,
            label = "Cart",
            isSelected = selectedRouteState == "cart",
            textColor = textColor
        ) {
            selectedRouteState = "cart"
            navController.navigate("cart")
        }
        FooterItem(
            iconResId = R.drawable.favorite,
            label = "Favourite",
            isSelected = selectedRouteState == "favourite",
            textColor = textColor
        ) {
            selectedRouteState = "favourite"
            navController.navigate("favourite")
        }
        FooterItem(
            iconResId = R.drawable.account,
            label = "Account",
            isSelected = selectedRouteState == "account",
            textColor = textColor
        ) {
            selectedRouteState = "account"
            navController.navigate("account")
        }
    }
}

@Composable
fun FooterItem(iconResId: Int, label: String, isSelected: Boolean, textColor: Color, onClick: () -> Unit) {
    val iconColor = if (isSelected) com.example.grupo_6.ui.theme.Splash else textColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconColor)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    Footer(navController = rememberNavController(), selectedRoute = "shop", isDarkMode = false)
}
