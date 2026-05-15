package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.*
import androidx.compose.ui.platform.LocalContext
import com.example.gramasuvidha.ShareUtils
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onProjectClick: (Int) -> Unit) {
    val context = LocalContext.current
    val isKannada = LanguageState.isKannada
    val totalBudget = sampleProjects.sumOf {
        it.budget.replace("₹", "").replace(",", "").toLong()
    }
    val avgProgress = sampleProjects.map { it.progress }.average().toInt()
    val inProgress = sampleProjects.count { it.status == "inprogress" }
    val completed = sampleProjects.count { it.status == "completed" }
    val planned = sampleProjects.count { it.status == "planned" }
    val total = sampleProjects.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಡ್ಯಾಶ್‌ಬೋರ್ಡ್" else "Dashboard",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Button(
                    onClick = {
                        ShareUtils.shareReport(
                            context = context,
                            isKannada = isKannada,
                            totalProjects = total,
                            completed = completed,
                            inProgress = inProgress,
                            planned = planned
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2D7A40))
                ) {
                    Icon(Icons.Default.Share, null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isKannada) "ವರದಿ ಹಂಚಿಕೊಳ್ಳಿ" else "Share Report",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Summary Cards
            item {
                Text(
                    if (isKannada) "ಒಟ್ಟು ಸಾರಾಂಶ" else "Overall Summary",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SummaryCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Folder,
                        value = "$total",
                        label = if (isKannada) "ಒಟ್ಟು" else "Total",
                        color = NavyBlue,
                        bgColor = Color(0xFFE8F0FB)
                    )
                    SummaryCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.TrendingUp,
                        value = "$avgProgress%",
                        label = if (isKannada) "ಸರಾಸರಿ" else "Avg Progress",
                        color = Color(0xFF2D7A40),
                        bgColor = Color(0xFFE8F5EC)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SummaryCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.CheckCircle,
                        value = "$completed",
                        label = if (isKannada) "ಮುಗಿದಿದೆ" else "Completed",
                        color = Color(0xFF2D7A40),
                        bgColor = Color(0xFFE8F5EC)
                    )
                    SummaryCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Schedule,
                        value = "$planned",
                        label = if (isKannada) "ಯೋಜನೆ" else "Planned",
                        color = Color(0xFFB45309),
                        bgColor = Color(0xFFFEF3E2)
                    )
                }
            }

            // Progress Bar Chart
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (isKannada) "ಯೋಜನೆ ಸ್ಥಿತಿ" else "Project Status",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(16.dp))
                        listOf(
                            Triple(
                                if (isKannada) "ಪ್ರಗತಿಯಲ್ಲಿದೆ" else "In Progress",
                                inProgress, Color(0xFF1A56B0)
                            ),
                            Triple(
                                if (isKannada) "ಪೂರ್ಣಗೊಂಡಿದೆ" else "Completed",
                                completed, Color(0xFF2D7A40)
                            ),
                            Triple(
                                if (isKannada) "ಯೋಜಿಸಲಾಗಿದೆ" else "Planned",
                                planned, Color(0xFFB45309)
                            )
                        ).forEach { (label, count, color) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    label,
                                    fontSize = 12.sp,
                                    modifier = Modifier.width(100.dp),
                                    color = Color.Gray
                                )
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(24.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color(0xFFF0F4FF))
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(count.toFloat() / total)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(color)
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "$count",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = color
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }

            // Budget Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = NavyBlue),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                if (isKannada) "ಒಟ್ಟು ಬಜೆಟ್" else "Total Budget",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp
                            )
                            Text(
                                "₹${String.format("%,.0f", totalBudget.toDouble())}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        }
                        Icon(
                            Icons.Default.AccountBalance,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.7f),
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            // Map Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (isKannada) "📍 ಯೋಜನೆಗಳ ನಕ್ಷೆ" else "📍 Projects Map",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))

                        // Map image placeholder
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE8F0FB)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("🗺", fontSize = 48.sp)
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    if (isKannada) "ಮಡುವೆಲೂರು ಗ್ರಾಮ" else "Maduvelu Village",
                                    fontWeight = FontWeight.Bold,
                                    color = NavyBlue,
                                    fontSize = 14.sp
                                )
                                Text(
                                    if (isKannada) "12 ಯೋಜನೆಗಳು ಗುರುತಿಸಲಾಗಿದೆ"
                                    else "12 projects marked",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        // Map markers info
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            LegendItem(
                                "🔴",
                                if (isKannada) "ಪ್ರಗತಿಯಲ್ಲಿ ($inProgress)"
                                else "In Progress ($inProgress)"
                            )
                            LegendItem(
                                "🟢",
                                if (isKannada) "ಪೂರ್ಣ ($completed)"
                                else "Completed ($completed)"
                            )
                            LegendItem(
                                "🟡",
                                if (isKannada) "ಯೋಜನೆ ($planned)"
                                else "Planned ($planned)"
                            )
                        }
                    }
                }
            }

            // Top Projects
            item {
                Text(
                    if (isKannada) "ಪ್ರಮುಖ ಯೋಜನೆಗಳು" else "Top Projects",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(8.dp))

                sampleProjects
                    .sortedByDescending { it.progress }
                    .take(5)
                    .forEach { project ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onProjectClick(project.id) },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            when (project.status) {
                                                "completed" -> Color(0xFFE8F5EC)
                                                "inprogress" -> Color(0xFFE8F0FB)
                                                else -> Color(0xFFFEF3E2)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        when {
                                            project.titleKannada.contains("ರಸ್ತೆ") -> "🛣"
                                            project.titleKannada.contains("ನೀರು") -> "💧"
                                            project.titleKannada.contains("ದೇವಸ್ಥಾನ") -> "🛕"
                                            project.titleKannada.contains("ಶಾಲಾ") -> "🏫"
                                            project.titleKannada.contains("ದೀಪ") -> "💡"
                                            else -> "🏗"
                                        },
                                        fontSize = 18.sp
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        if (isKannada) project.titleKannada
                                        else project.titleEnglish,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 13.sp,
                                        maxLines = 1
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    LinearProgressIndicator(
                                        progress = { project.progress / 100f },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(4.dp)
                                            .clip(RoundedCornerShape(2.dp)),
                                        color = when (project.status) {
                                            "completed" -> Color(0xFF2D7A40)
                                            "inprogress" -> NavyBlue
                                            else -> Color(0xFFB45309)
                                        },
                                        trackColor = Color(0xFFF0F4FF)
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "${project.progress}%",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = NavyBlue
                                )
                            }
                        }
                    }
            }
        }
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value: String,
    label: String,
    color: Color,
    bgColor: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = color)
                Text(label, fontSize = 11.sp, color = color.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
fun LegendItem(icon: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon, fontSize = 12.sp)
        Spacer(Modifier.width(4.dp))
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}