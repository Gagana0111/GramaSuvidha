package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapDemoScreen(onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada
    var selectedProject by remember { mutableStateOf<Int?>(null) }

    val projectLocations = listOf(
        mapOf("id" to 1, "lat" to "13.9299", "lng" to "75.5681",
            "ward" to "Ward 4", "color" to "blue"),
        mapOf("id" to 2, "lat" to "13.9310", "lng" to "75.5695",
            "ward" to "East Street", "color" to "green"),
        mapOf("id" to 3, "lat" to "13.9285", "lng" to "75.5670",
            "ward" to "Village Center", "color" to "red"),
        mapOf("id" to 4, "lat" to "13.9320", "lng" to "75.5710",
            "ward" to "Main Street", "color" to "orange"),
        mapOf("id" to 5, "lat" to "13.9265", "lng" to "75.5650",
            "ward" to "Various Wards", "color" to "purple")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಯೋಜನೆ ನಕ್ಷೆ" else "Projects Map",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
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

            // Map View
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box {
                        // Map image
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1524661135-423995f22d0b?w=800",
                            contentDescription = "Map",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                        )

                        // Dark overlay
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .background(Color.Black.copy(alpha = 0.3f))
                        )

                        // Map markers overlay
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                        ) {
                            // Marker 1 - Road
                            MapMarker(
                                modifier = Modifier
                                    .offset(x = 80.dp, y = 100.dp),
                                color = Color(0xFF1A56B0),
                                label = "1",
                                isSelected = selectedProject == 1,
                                onClick = { selectedProject = if (selectedProject == 1) null else 1 }
                            )
                            // Marker 2 - Temple
                            MapMarker(
                                modifier = Modifier
                                    .offset(x = 160.dp, y = 80.dp),
                                color = Color(0xFF2D7A40),
                                label = "2",
                                isSelected = selectedProject == 2,
                                onClick = { selectedProject = if (selectedProject == 2) null else 2 }
                            )
                            // Marker 3 - Water
                            MapMarker(
                                modifier = Modifier
                                    .offset(x = 120.dp, y = 160.dp),
                                color = Color(0xFFCC2222),
                                label = "3",
                                isSelected = selectedProject == 3,
                                onClick = { selectedProject = if (selectedProject == 3) null else 3 }
                            )
                            // Marker 4 - Drainage
                            MapMarker(
                                modifier = Modifier
                                    .offset(x = 200.dp, y = 130.dp),
                                color = Color(0xFFB45309),
                                label = "4",
                                isSelected = selectedProject == 4,
                                onClick = { selectedProject = if (selectedProject == 4) null else 4 }
                            )
                            // Marker 5 - Jal Jeevan
                            MapMarker(
                                modifier = Modifier
                                    .offset(x = 60.dp, y = 180.dp),
                                color = Color(0xFF7B1FA2),
                                label = "5",
                                isSelected = selectedProject == 5,
                                onClick = { selectedProject = if (selectedProject == 5) null else 5 }
                            )
                        }

                        // Map controls
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            MapControlButton(icon = Icons.Default.Add)
                            MapControlButton(icon = Icons.Default.Remove)
                            MapControlButton(icon = Icons.Default.MyLocation)
                        }

                        // Map type label
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black.copy(alpha = 0.6f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "Google Maps • Maduvelu Village",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            // Legend
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LegendDot(color = Color(0xFF1A56B0),
                            label = if (isKannada) "ಪ್ರಗತಿಯಲ್ಲಿ" else "In Progress")
                        LegendDot(color = Color(0xFF2D7A40),
                            label = if (isKannada) "ಪೂರ್ಣ" else "Completed")
                        LegendDot(color = Color(0xFFB45309),
                            label = if (isKannada) "ಯೋಜನೆ" else "Planned")
                    }
                }
            }

            // Selected project popup
            selectedProject?.let { id ->
                val project = sampleProjects.find { it.id == id }
                project?.let {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = NavyBlue),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("📍", fontSize = 24.sp)
                                Spacer(Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        if (isKannada) project.titleKannada
                                        else project.titleEnglish,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        if (isKannada) project.locationKannada
                                        else project.locationEnglish,
                                        color = Color.White.copy(alpha = 0.8f),
                                        fontSize = 12.sp
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    LinearProgressIndicator(
                                        progress = { project.progress / 100f },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(4.dp)
                                            .clip(RoundedCornerShape(2.dp)),
                                        color = Color(0xFF4CAF50),
                                        trackColor = Color.White.copy(alpha = 0.3f)
                                    )
                                    Text(
                                        "${project.progress}% • ${project.budget}",
                                        color = Color.White.copy(alpha = 0.8f),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // All project locations list
            item {
                Text(
                    if (isKannada) "📍 ಎಲ್ಲಾ ಯೋಜನೆ ಸ್ಥಳಗಳು"
                    else "📍 All Project Locations",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
            }

            items(sampleProjects.take(6).size) { index ->
                val project = sampleProjects[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedProject = project.id },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedProject == project.id)
                            Color(0xFFE8F0FB) else Color.White
                    ),
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
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    when (project.status) {
                                        "completed" -> Color(0xFF2D7A40)
                                        "inprogress" -> NavyBlue
                                        else -> Color(0xFFB45309)
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "${index + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                if (isKannada) project.titleKannada
                                else project.titleEnglish,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                            Text(
                                if (isKannada) project.locationKannada
                                else project.locationEnglish,
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }
                        Icon(
                            Icons.Default.LocationOn,
                            null,
                            tint = NavyBlue,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun MapMarker(
    modifier: Modifier,
    color: Color,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(if (isSelected) 36.dp else 28.dp)
            .clip(CircleShape)
            .background(if (isSelected) color else color.copy(alpha = 0.8f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = if (isSelected) 14.sp else 11.sp
        )
    }
}

@Composable
fun MapControlButton(icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = Color.DarkGray, modifier = Modifier.size(16.dp))
    }
}

@Composable
fun LegendDot(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(4.dp))
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}