package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada

    val totalBudget = 1_50_00_000L
    val spentBudget = 89_50_000L
    val remainingBudget = totalBudget - spentBudget
    val spentPercent = (spentBudget.toFloat() / totalBudget * 100).toInt()

    val categoryBudgets = listOf(
        Triple(if (isKannada) "🛣 ರಸ್ತೆ" else "🛣 Roads",
            30_00_000L, Color(0xFF1A56B0)),
        Triple(if (isKannada) "💧 ನೀರು" else "💧 Water",
            60_00_000L, Color(0xFF0288D1)),
        Triple(if (isKannada) "🏫 ಶಿಕ್ಷಣ" else "🏫 Education",
            8_50_000L, Color(0xFF2D7A40)),
        Triple(if (isKannada) "🏛 ಕಟ್ಟಡ" else "🏛 Buildings",
            43_00_000L, Color(0xFF7B1FA2)),
        Triple(if (isKannada) "💡 ವಿದ್ಯುತ್" else "💡 Electricity",
            3_20_000L, Color(0xFFB45309)),
        Triple(if (isKannada) "☀ ಸೌರ" else "☀ Solar",
            12_00_000L, Color(0xFFE64A19))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಬಜೆಟ್ ವಿವರ" else "Budget Tracker",
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Total Budget Overview
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NavyBlue),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            if (isKannada) "ಒಟ್ಟು ಬಜೆಟ್" else "Total Budget",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 13.sp
                        )
                        Text(
                            "₹1,50,00,000",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    if (isKannada) "ಖರ್ಚಾಗಿದೆ" else "Spent",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                                Text(
                                    "₹89,50,000",
                                    color = Color(0xFFFFD54F),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    if (isKannada) "ಉಳಿದಿದೆ" else "Remaining",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                                Text(
                                    "₹60,50,000",
                                    color = Color(0xFF80CBC4),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = { spentPercent / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = Color(0xFFFFD54F),
                            trackColor = Color.White.copy(alpha = 0.3f)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "$spentPercent% ${if (isKannada) "ಬಳಕೆಯಾಗಿದೆ" else "utilized"}",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp
                        )
                    }
                }
            }

            // Category Breakdown
            item {
                Text(
                    if (isKannada) "ವಿಭಾಗವಾರು ಬಜೆಟ್" else "Category-wise Budget",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
            }

            items(categoryBudgets.size) { index ->
                val (name, budget, color) = categoryBudgets[index]
                val percent = (budget.toFloat() / totalBudget * 100).toInt()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            Text(
                                "₹${String.format("%,.0f", budget.toDouble())}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = color
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                progress = { percent / 100f },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                color = color,
                                trackColor = Color(0xFFF0F4FF)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "$percent%",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = color
                            )
                        }
                    }
                }
            }

            // Project-wise Budget
            item {
                Spacer(Modifier.height(4.dp))
                Text(
                    if (isKannada) "ಯೋಜನಾವಾರು ಬಜೆಟ್" else "Project-wise Budget",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
            }

            items(sampleProjects.size) { index ->
                val project = sampleProjects[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                if (isKannada) project.titleKannada else project.titleEnglish,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                            Text(
                                if (isKannada) project.locationKannada else project.locationEnglish,
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                project.budget,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = NavyBlue
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when (project.status) {
                                            "completed" -> Color(0xFFE8F5EC)
                                            "inprogress" -> Color(0xFFE8F0FB)
                                            else -> Color(0xFFFEF3E2)
                                        }
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    "${project.progress}%",
                                    fontSize = 11.sp,
                                    color = when (project.status) {
                                        "completed" -> Color(0xFF2D7A40)
                                        "inprogress" -> Color(0xFF1A56B0)
                                        else -> Color(0xFFB45309)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}