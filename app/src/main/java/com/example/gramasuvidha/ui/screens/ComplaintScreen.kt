package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.gramasuvidha.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    val categories = if (LanguageState.isKannada)
        listOf("🛣 ರಸ್ತೆ", "💧 ನೀರು", "💡 ವಿದ್ಯುತ್", "🗑 ಸ್ವಚ್ಛತೆ", "🏗 ಕಟ್ಟಡ", "📋 ಇತರೆ")
    else
        listOf("🛣 Road", "💧 Water", "💡 Electricity", "🗑 Sanitation", "🏗 Building", "📋 Other")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (LanguageState.isKannada) "ದೂರು ಸಲ್ಲಿಸಿ" else "File Complaint",
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        if (submitted) {
            // Success Screen
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF2D7A40),
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    if (LanguageState.isKannada) "ದೂರು ಯಶಸ್ವಿಯಾಗಿ ಸಲ್ಲಿಸಲಾಗಿದೆ!" else "Complaint Submitted Successfully!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF2D7A40)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    if (LanguageState.isKannada) "ನಿಮ್ಮ ದೂರು ಸಂಖ್ಯೆ: #GS2024001" else "Your complaint ID: #GS2024001",
                    color = Color.Gray, fontSize = 14.sp
                )
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (LanguageState.isKannada) "ಹಿಂದೆ ಹೋಗಿ" else "Go Back")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Personal Info Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ವೈಯಕ್ತಿಕ ಮಾಹಿತಿ" else "Personal Info",
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(if (LanguageState.isKannada) "ಹೆಸರು" else "Name") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { if (it.length <= 10) phone = it },
                            label = { Text(if (LanguageState.isKannada) "ಮೊಬೈಲ್" else "Mobile") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true
                        )
                    }
                }

                // Category Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ವಿಭಾಗ ಆಯ್ಕೆ ಮಾಡಿ" else "Select Category",
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))
                        // Grid of categories
                        val rows = categories.chunked(3)
                        rows.forEach { row ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                row.forEach { category ->
                                    val isSelected = selectedCategory == category
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(
                                                if (isSelected) NavyBlue
                                                else Color(0xFFF0F4FF)
                                            )
                                            .clickable { selectedCategory = category }
                                            .padding(vertical = 10.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            category,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (isSelected) Color.White
                                            else Color.DarkGray
                                        )
                                    }
                                }
                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }

                // Description Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ವಿವರಣೆ" else "Description",
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            placeholder = {
                                Text(
                                    if (LanguageState.isKannada)
                                        "ಸಮಸ್ಯೆಯ ವಿವರ ತಿಳಿಸಿ..."
                                    else "Describe the issue..."
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }

                // Submit Button
                Button(
                    onClick = { submitted = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                    enabled = name.isNotEmpty() && phone.length == 10 && selectedCategory.isNotEmpty()
                ) {
                    Text(
                        if (LanguageState.isKannada) "ದೂರು ಸಲ್ಲಿಸಿ" else "Submit Complaint",
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}