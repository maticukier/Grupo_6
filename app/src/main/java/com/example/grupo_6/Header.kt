package com.example.grupo_6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Header(title: String, isDarkMode: Boolean) {
    val backgroundColor = if (isDarkMode) Color(0xFF1E1E1E) else Color(0xFFFCFCFC)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(backgroundColor)
            .padding(start = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Navigation Icon",
                tint = textColor
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            text = title,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.align(Alignment.Center),
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header(title = "Shop", isDarkMode = false)
}

