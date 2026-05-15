package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.DarkBlue
import com.example.gramasuvidha.ui.theme.LightBlue
import com.example.gramasuvidha.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {

    // This line makes SettingsScreen react to LanguageState changes
    val isKannada = LanguageState.isKannada

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಸೆಟ್ಟಿಂಗ್ಸ್" else "Settings",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        Spacer(Modifier.height(16.dp))

        // Language Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    if (isKannada) "ಭಾಷೆ ಆಯ್ಕೆ" else "Select Language",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )

                Spacer(Modifier.height(16.dp))

                // Kannada Option
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isKannada) LightBlue else Color.Transparent)
                        .clickable { LanguageState.isKannada = true }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isKannada,
                        onClick = { LanguageState.isKannada = true },
                        colors = RadioButtonDefaults.colors(selectedColor = NavyBlue)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("ಕನ್ನಡ", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text("Kannada", fontSize = 12.sp, color = Color.Gray)
                    }
                    if (isKannada) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(NavyBlue)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("Active", color = Color.White, fontSize = 11.sp)
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // English Option
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (!isKannada) LightBlue else Color.Transparent)
                        .clickable { LanguageState.isKannada = false }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !isKannada,
                        onClick = { LanguageState.isKannada = false },
                        colors = RadioButtonDefaults.colors(selectedColor = NavyBlue)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("English", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text("ಇಂಗ್ಲಿಷ್", fontSize = 12.sp, color = Color.Gray)
                    }
                    if (!isKannada) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(NavyBlue)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("Active", color = Color.White, fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // App Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    if (isKannada) "ಅಪ್ಲಿಕೇಶನ್ ಬಗ್ಗೆ" else "About App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        if (isKannada) "ಆವೃತ್ತಿ" else "Version",
                        color = Color.Gray, fontSize = 14.sp
                    )
                    Text("1.0.0", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                }
                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        if (isKannada) "ಅಭಿವೃದ್ಧಿಪಡಿಸಿದವರು" else "Developed by",
                        color = Color.Gray, fontSize = 14.sp
                    )
                    Text(
                        "Gram Panchayat",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Back to Home Button
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)
        ) {
            Text(
                if (isKannada) "ಹಿಂದೆ ಹೋಗಿ" else "Go Back",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}