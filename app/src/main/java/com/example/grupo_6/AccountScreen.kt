package com.example.grupo_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AccountScreen(navController: NavHostController, isDarkMode: Boolean, toggleDarkMode: (Boolean) -> Unit) {
    val poppins = FontFamily(Font(R.font.poppins_regular))
    val textStyle = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Normal)

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    Header(title = "Account", isDarkMode = isDarkMode)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "Afsar Hossen",
                                    style = textStyle.copy(
                                        fontWeight = FontWeight.Bold // Aplicar negrita
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.edit),
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            Text(
                                "imshuvo97@gmail.com",
                                style = textStyle.copy(
                                    color = Color.Gray // Cambiar el color a gris
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    AccountMenuItem("Orders", R.drawable.order, textStyle)
                    AccountMenuItem("My Details", R.drawable.details, textStyle)
                    AccountMenuItem("Delivery Address", R.drawable.delivery, textStyle)
                    AccountMenuItem("Payment Methods", R.drawable.payment, textStyle)
                    AccountMenuItem("Promo Card", R.drawable.promo, textStyle)
                    AccountMenuItem("Notifications", R.drawable.notification, textStyle)
                    AccountMenuItem("Help", R.drawable.help, textStyle)

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Dark mode", style = textStyle)
                        CustomSwitch(checked = isDarkMode, onCheckedChange = { toggleDarkMode(it) })
                    }

                    Button(
                        onClick = { navController.navigate("signup") }, // Navigate to SignUpScreen
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.botonLog), contentColor = colorResource(id = R.color.Splash)),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "Logout",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Log Out", style = textStyle)
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { navController.navigate("signup") }, // Navigate to SignUpScreen
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.botonLog), contentColor = colorResource(id = R.color.Splash)),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.logout),
                                    contentDescription = "Logout",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Log Out", style = textStyle)
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }// Para empujar los botones hacia el fondo
                }




                Footer(
                    navController = navController,
                    selectedRoute = "account",
                    isDarkMode = isDarkMode,
                    modifier = Modifier.align(Alignment.BottomCenter) // Alinea el Footer al fondo
                )
            }
        }
    )
}

@Composable
fun AccountMenuItem(title: String, iconResId: Int, textStyle: TextStyle) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                style = textStyle.copy(
                    fontWeight = FontWeight.Bold // Aplicar negrita
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Go to $title",
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Color(0xFF53B175),
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color.LightGray.copy(alpha = 0.5f),
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    var isDarkMode by remember { mutableStateOf(false) }
    AccountScreen(navController = rememberNavController(), isDarkMode = isDarkMode, toggleDarkMode = { isDarkMode = it })
}

object Account {
    private var darkMode: Boolean = false // Estado inicial

    fun isDarkMode(): Boolean {
        return darkMode
    }

    fun toggleDarkMode(isEnabled: Boolean) {
        darkMode = isEnabled
    }
}
