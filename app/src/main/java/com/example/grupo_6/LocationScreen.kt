package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationScreen(){
    //Columna Grande con contenidos
    Column{
        //Columna del icono
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color(255,255,255)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
        }
        //Columna del texto
        Column(modifier = Modifier
            .height(200.dp)
            .background(Color(255,255,255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Select Your Location", style =
                TextStyle(
                    fontSize = 26.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Switch your location to stay in tune with what's happening in your area", style =
                TextStyle(
                    fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center
                )
            )
        }
        //Columna del DropDown
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(255,255,255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your Zone", style = TextStyle(fontSize = 16.sp, color = Color.Gray), modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, bottom = 8.dp))
            DropdownMenuExample("Select your zone")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Your Area", style = TextStyle(fontSize = 16.sp, color = Color.Gray), modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, bottom = 8.dp))
            DropdownMenuExample("Select your area")
        }

        //Columna del boton
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(255,255,255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* Handle logout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Splash), contentColor = Color.White),
                shape = RoundedCornerShape(18.dp)
            ){
                Text("Submit", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun DropdownMenuExample(placeholder: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth(0.9f)) {
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Transparent)
                .border( 1.dp, Color.Transparent , RoundedCornerShape(8.dp)),
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
                    style = TextStyle(fontSize = 16.sp)
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
            val options = listOf("Option 1", "Option 2", "Option 3")
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
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
    LocationScreen()
}