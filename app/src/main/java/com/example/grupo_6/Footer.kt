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
fun Footer(navController: NavHostController, selectedRoute: String, modifier: Modifier = Modifier) {
    var selectedRouteState by remember { mutableStateOf(selectedRoute) }

    Row(
        modifier = modifier
            .width(414.dp)
            .height(92.dp)
            .background(Color(0xFFFCFCFC))
            .padding(horizontal = 16.dp)
            .zIndex(15f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FooterItem(
            iconResId = R.drawable.shop,
            label = "Shop",
            isSelected = selectedRouteState == "shop"
        ) {
            selectedRouteState = "shop"
            navController.navigate("shop")
        }
        FooterItem(
            iconResId = R.drawable.search,
            label = "Explore",
            isSelected = selectedRouteState == "explore"
        ) {
            selectedRouteState = "explore"
            navController.navigate("explore")
        }
        FooterItem(
            iconResId = R.drawable.cart,
            label = "Cart",
            isSelected = selectedRouteState == "cart"
        ) {
            selectedRouteState = "cart"
            navController.navigate("cart")
        }
        FooterItem(
            iconResId = R.drawable.favorite,
            label = "Favourite",
            isSelected = selectedRouteState == "favorite"
        ) {
            selectedRouteState = "favorite"
            navController.navigate("favorite")
        }
        FooterItem(
            iconResId = R.drawable.account,
            label = "Account",
            isSelected = selectedRouteState == "account"
        ) {
            selectedRouteState = "account"
            navController.navigate("account")
        }
    }
}

@Composable
fun FooterItem(iconResId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    val iconColor = if (isSelected) Color.Green else Color.Black

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(24.dp), // Adjust the size as needed
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconColor)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = iconColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    Footer(navController = rememberNavController(), selectedRoute = "shop")
}