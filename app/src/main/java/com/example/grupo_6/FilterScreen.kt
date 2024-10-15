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

@Composable
fun FilterScreen(navController: NavHostController, isDarkMode: Boolean){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
                Spacer(modifier = Modifier.height(30.dp))
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "Cross",
                    modifier = Modifier
                        .size(25.dp)
                        .offset(x = 15.dp)
                        .clickable {
                            navController.navigate("explore")
                        }
                )
                Text("Filters", style =
                TextStyle(
                    fontSize = 21.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                ),
                    modifier = Modifier.align(Alignment.Center)
                )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier
                .background(Color(242,243,242))
                .fillMaxWidth()
                .height(600.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    "Categories", style =
                    TextStyle(
                        fontSize = 21.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            val items = listOf("Eggs", "Noodles & Pasta", "Chips & Crisps", "Fast Food")
            Checklist(items)
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    "Brands", style =
                    TextStyle(
                        fontSize = 21.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            val items2 = listOf("Individual Collection", "Cocola", "Ifad", "Kazi Farmas")
            Checklist(items2)
        }
        Column(
            modifier = Modifier
                .background(Color(242,243,242))
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Button(
                onClick = { navController.navigate("explore") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text("Apply Filter", color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }

}

@Composable
fun Checklist(items: List<String>) {
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
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Label for each item
                Text(
                    text = item,
                    modifier = Modifier.weight(1f)
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