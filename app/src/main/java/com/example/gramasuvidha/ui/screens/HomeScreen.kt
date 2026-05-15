package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.data.Project
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProjectClick: (Int) -> Unit,
    onSettingsClick: () -> Unit,
    onComplaintClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    val filteredProjects = when (selectedTab) {
        0 -> sampleProjects
        1 -> sampleProjects.filter { it.status == "inprogress" }
        2 -> sampleProjects.filter { it.status == "completed" }
        else -> sampleProjects
    }

    val inProgressCount = sampleProjects.count { it.status == "inprogress" }
    val completedCount = sampleProjects.count { it.status == "completed" }
    val plannedCount = sampleProjects.count { it.status == "planned" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // ✅ ONE actions block only
        TopAppBar(
            title = {
                Text(
                    "Grama Suvidha",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            actions = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(Icons.Default.Search, null, tint = Color.White)
                }
                IconButton(onClick = { onNotificationClick() }) {
                    BadgedBox(badge = { Badge { Text("3") } }) {
                        Icon(Icons.Default.Notifications, null, tint = Color.White)
                    }
                }
                TextButton(onClick = { LanguageState.isKannada = !LanguageState.isKannada }) {
                    Text(
                        if (LanguageState.isKannada) "EN" else "ಕನ್ನಡ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            // Weather Widget
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NavyBlue),
                    elevation = CardDefaults.cardElevation(4.dp)
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
                                if (LanguageState.isKannada) "ಮಡುವೆಲೂರು ಗ್ರಾಮ"
                                else "Maduvelu Village",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                            Text(
                                "32°C",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp
                            )
                            Text(
                                if (LanguageState.isKannada) "ಭಾಗಶಃ ಮೋಡ" else "Partly Cloudy",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("⛅", fontSize = 48.sp)
                            Spacer(Modifier.height(4.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("💧", fontSize = 14.sp)
                                    Text("68%", color = Color.White, fontSize = 11.sp)
                                    Text(
                                        if (LanguageState.isKannada) "ತೇವ" else "Humidity",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 10.sp
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("💨", fontSize = 14.sp)
                                    Text("12km/h", color = Color.White, fontSize = 11.sp)
                                    Text(
                                        if (LanguageState.isKannada) "ಗಾಳಿ" else "Wind",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Header Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(NavyBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🏘", fontSize = 24.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                if (LanguageState.isKannada)
                                    "ಗ್ರಾಮ-ಸುವಿಧಾ ಪೋರ್ಟಲ್"
                                else
                                    "Grama Suvidha Portal",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                if (LanguageState.isKannada)
                                    "ಗ್ರಾಮ ಡಿಜಿಟಲ್ ಮಾಹಿತಿ ಫಲಕ"
                                else
                                    "Village Digital Info Board",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onSettingsClick() }
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Stat Cards
            item {
                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    StatCard(
                        icon = "🕐",
                        label = if (LanguageState.isKannada) "ಪ್ರಗತಿಯಲ್ಲಿದೆ" else "In Progress",
                        count = inProgressCount,
                        bgColor = LightBlue,
                        textColor = DarkBlue
                    )
                    Spacer(Modifier.height(8.dp))
                    StatCard(
                        icon = "✅",
                        label = if (LanguageState.isKannada) "ಪೂರ್ಣಗೊಂಡಿದೆ" else "Completed",
                        count = completedCount,
                        bgColor = LightGreen,
                        textColor = GreenColor
                    )
                    Spacer(Modifier.height(8.dp))
                    StatCard(
                        icon = "📋",
                        label = if (LanguageState.isKannada) "ಯೋಜಿಸಲಾಗಿದೆ" else "Planned",
                        count = plannedCount,
                        bgColor = LightAmber,
                        textColor = AmberColor
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }

            // Filter Tabs
            item {
                val tabs = if (LanguageState.isKannada)
                    listOf("ಗ್ರಾಮ ಯೋಜನೆಗಳು", "ಪ್ರಗತಿಯಲ್ಲಿದೆ", "ಪೂರ್ಣಗೊಂಡಿದೆ")
                else
                    listOf("All Projects", "In Progress", "Completed")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        val isSelected = selectedTab == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    if (isSelected) Color.White else Color.Transparent
                                )
                                .clickable { selectedTab = index }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold
                                else FontWeight.Normal,
                                color = if (isSelected) Color.Black else Color.Gray
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            // Project Cards
            items(filteredProjects) { project ->
                ProjectCard(
                    project = project,
                    onClick = { onProjectClick(project.id) }
                )
                Spacer(Modifier.height(10.dp))
            }

            // Complaint Button
            item {
                Button(
                    onClick = { onComplaintClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCC2222)
                    )
                ) {
                    Text("⚠ ", fontSize = 16.sp)
                    Text(
                        if (LanguageState.isKannada) "ದೂರು ಸಲ್ಲಿಸಿ" else "File a Complaint",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StatCard(
    icon: String,
    label: String,
    count: Int,
    bgColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(icon, fontSize = 22.sp)
            Spacer(Modifier.width(12.dp))
            Text(
                label,
                color = textColor,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Spacer(Modifier.weight(1f))
            Text(
                count.toString(),
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun ProjectCard(project: Project, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = when {
                        project.titleKannada.contains("ರಸ್ತೆ") ->
                            "https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?w=400"
                        project.titleKannada.contains("ನೀರು") ->
                            "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400"
                        project.titleKannada.contains("ದೇವಸ್ಥಾನ") ->
                            "https://images.unsplash.com/photo-1582510003544-4d00b7f74220?w=400"
                        project.titleKannada.contains("ಒಳಚರಂಡಿ") ->
                            "https://images.unsplash.com/photo-1504307651254-35680f356dfd?w=400"
                        project.titleKannada.contains("ಮಾರುಕಟ್ಟೆ") ->
                            "https://images.unsplash.com/photo-1542838132-92c53300491e?w=400"
                        project.titleKannada.contains("ಶಾಲಾ") ->
                            "https://images.unsplash.com/photo-1580582932707-520aed937b7b?w=400"
                        project.titleKannada.contains("ಸಮುದಾಯ") ->
                            "https://images.unsplash.com/photo-1497366216548-37526070297c?w=400"
                        project.titleKannada.contains("ದೀಪ") ->
                            "https://images.unsplash.com/photo-1565608087341-404b25492fee?w=400"
                        project.titleKannada.contains("ಸೌರ") ->
                            "https://images.unsplash.com/photo-1509391366360-2e959784a276?w=400"
                        project.titleKannada.contains("ಮಳೆ") ->
                            "https://images.unsplash.com/photo-1519692933481-e162a57d6721?w=400"
                        else ->
                            "https://images.unsplash.com/photo-1504307651254-35680f356dfd?w=400"
                    },
                    contentDescription = project.titleKannada,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                // Status Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            when (project.status) {
                                "inprogress" -> GreenColor
                                "completed" -> DarkBlue
                                else -> AmberColor
                            }
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        when (project.status) {
                            "inprogress" -> if (LanguageState.isKannada)
                                "ಪ್ರಗತಿಯಲ್ಲಿದೆ" else "In Progress"
                            "completed" -> if (LanguageState.isKannada)
                                "ಪೂರ್ಣಗೊಂಡಿದೆ" else "Completed"
                            else -> if (LanguageState.isKannada)
                                "ಯೋಜಿಸಲಾಗಿದೆ" else "Planned"
                        },
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Card Body
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    if (LanguageState.isKannada) project.titleKannada
                    else project.titleEnglish,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                )
                Text(
                    if (LanguageState.isKannada) project.locationKannada
                    else project.locationEnglish,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        if (LanguageState.isKannada) "ಒಟ್ಟು ಪ್ರಗತಿ" else "Total Progress",
                        color = DarkBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        "${project.progress}%",
                        color = DarkBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { project.progress / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = NavyBlue,
                    trackColor = Color(0xFFE0E7F7)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        project.budget,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Text(
                        if (LanguageState.isKannada) "ವಿವರಗಳು >" else "Details >",
                        color = DarkBlue,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}