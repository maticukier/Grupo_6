package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun Footer() {
    Row(
        modifier = Modifier
            .width(414.dp)
            .height(92.dp)
            .background(Color(0xFFFCFCFC))
            .padding(horizontal = 16.dp)
            .zIndex(15f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FooterItem(iconResId = R.drawable.shop, label = "Shop")
        FooterItem(iconResId = R.drawable.search, label = "Explore")
        FooterItem(iconResId = R.drawable.cart, label = "Cart")
        FooterItem(iconResId = R.drawable.favorite, label = "Favourite")
        FooterItem(iconResId = R.drawable.account, label = "Account")
    }
}

@Composable
fun FooterItem(iconResId: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(24.dp) // Adjust the size as needed
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    Footer()
}