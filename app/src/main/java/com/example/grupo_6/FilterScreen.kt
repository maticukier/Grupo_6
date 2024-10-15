package com.example.grupo_6

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily


@Composable
fun FilterScreen(navController: NavHostController, isDarkMode: Boolean) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val sectionBackgroundColor = if (isDarkMode) Color(0xFF1F1F1F) else Color(0xFFF2F3F2)
    val buttonColor = if (isDarkMode) Color(0xFF53B175) else Color(0xFF53B175)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Icon(
                Icons.Rounded.Close,
                contentDescription = "Cross",
                tint = textColor,
                modifier = Modifier
                    .size(25.dp)
                    .offset(x = 15.dp)
                    .clickable {
                        navController.navigate("explore")
                    }
            )
            Text(
                "Filters",
                style = textStyle.copy(
                    fontSize = 21.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .background(sectionBackgroundColor)
                .fillMaxWidth()
                .height(600.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    "Categories", style = textStyle.copy(
                        fontSize = 21.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            val items = listOf("Eggs", "Noodles & Pasta", "Chips & Crisps", "Fast Food")
            Checklist(items, textStyle, textColor)
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    "Brands", style = textStyle.copy(
                        fontSize = 21.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            val items2 = listOf("Individual Collection", "Cocola", "Ifad", "Kazi Farmas")
            Checklist(items2, textStyle, textColor)
        }
        Column(
            modifier = Modifier
                .background(sectionBackgroundColor)
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Button(
                onClick = { navController.navigate("explore") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text("Apply Filter", color = Color.White, modifier = Modifier.padding(vertical = 8.dp), style = textStyle)
            }
        }
    }
}

@Composable
fun Checklist(items: List<String>, textStyle: TextStyle, textColor: Color) {
    // State to hold the checked status of each item
    val checkedStates = remember { mutableStateListOf(*Array(items.size) { false }) }

    // Column layout for the checklist
    LazyColumn {
        itemsIndexed(items) { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                // Checkbox for each item
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { checked ->
                        checkedStates[index] = checked
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = if (checkedStates[index]) textColor else Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Label for each item
                Text(
                    text = item,
                    modifier = Modifier.weight(1f),
                    style = textStyle.copy(color = textColor)
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterScreenPreview(){
    val navController = rememberNavController()
    val isDarkMode = false
    FilterScreen(navController, isDarkMode)
}
@Preview
@Composable
fun FilterScreenDarkModePreview(){
    val navController = rememberNavController()
    val isDarkMode = true
    FilterScreen(navController, isDarkMode)
}