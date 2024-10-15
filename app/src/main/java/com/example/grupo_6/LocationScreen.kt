package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LocationScreen(navController: NavController) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)

    Column {
        // Row for the back arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(15.dp) // Add padding to move the arrow away from the edge
                    .size(50.dp)
                    .clickable { navController.navigate("login") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flechaatras),
                    contentDescription = "Back Arrow",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Rest of the content
        Column {
            //Columna del icono
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(255, 255, 255)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "Logo",
                    modifier = Modifier.size(200.dp)
                )
            }
            //Columna del texto
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .background(Color(255, 255, 255)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Select Your Location", style = textStyle.copy(
                        fontSize = 26.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Switch your location to stay in tune with what's happening in your area", style = textStyle.copy(
                        fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            //Columna del DropDown
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(255, 255, 255)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Your Zone",
                    style = textStyle.copy(
                        fontSize = 16.sp,
                        color = Color.DarkGray, // Use a darker gray color
                        fontWeight = FontWeight.ExtraBold // Set font weight to ExtraBold
                    ),
                    modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, bottom = 8.dp)
                )
                DropdownMenuExample("Select your zone", listOf("Banasree", "Jakarta", "Frankfurt"), textStyle)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Your Area",
                    style = textStyle.copy(
                        fontSize = 16.sp,
                        color = Color.DarkGray, // Use a darker gray color
                        fontWeight = FontWeight.ExtraBold // Set font weight to ExtraBold
                    ),
                    modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, bottom = 8.dp)
                )
                DropdownMenuExample("Select your area", listOf("Bangladesh", "Indonesia", "Germany"), textStyle)
            }

            //Columna del boton
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(255, 255, 255)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("shop") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash), contentColor = Color.White),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text("Submit", fontSize = 18.sp, style = textStyle)
                }
            }
        }
    }
}

@Composable
fun DropdownMenuExample(placeholder: String, options: List<String>, textStyle: TextStyle) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth(0.9f)) {
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Transparent)
                .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = if (selectedOption.isEmpty()) Color.Gray else Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            border = null,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedOption.isEmpty()) placeholder else selectedOption,
                    style = textStyle.copy(fontSize = 16.sp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.Gray
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = textStyle) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LocationPreview() {
    val navController = rememberNavController()
    LocationScreen(navController = navController)
}