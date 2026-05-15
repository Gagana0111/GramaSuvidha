package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen() {
    val isKannada = LanguageState.isKannada

    val announcements = listOf(
        mapOf(
            "title" to "Gram Sabha Meeting",
            "titleKn" to "ಗ್ರಾಮಸಭೆ ಸಭೆ",
            "desc" to "Monthly Gram Sabha meeting will be held on Sunday 10 AM at Community Hall. All villagers are requested to attend.",
            "descKn" to "ಮಾಸಿಕ ಗ್ರಾಮಸಭೆ ಭಾನುವಾರ ಬೆಳಿಗ್ಗೆ 10 ಗಂಟೆಗೆ ಸಮುದಾಯ ಭವನದಲ್ಲಿ ನಡೆಯಲಿದೆ.",
            "date" to "May 12, 2024",
            "tag" to "Meeting",
            "tagKn" to "ಸಭೆ",
            "color" to "blue",
            "image" to "https://images.unsplash.com/photo-1577896851231-70ef18881754?w=400"
        ),
        mapOf(
            "title" to "Water Supply Schedule",
            "titleKn" to "ನೀರು ಸರಬರಾಜು ವೇಳಾಪಟ್ಟಿ",
            "desc" to "Due to pipeline maintenance, water supply will be suspended from 9 AM to 2 PM on Thursday.",
            "descKn" to "ಪೈಪ್‌ಲೈನ್ ನಿರ್ವಹಣೆಯಿಂದಾಗಿ ಗುರುವಾರ ಬೆಳಿಗ್ಗೆ 9 ರಿಂದ 2 ರವರೆಗೆ ನೀರು ಸರಬರಾಜು ಸ್ಥಗಿತಗೊಳ್ಳಲಿದೆ.",
            "date" to "May 10, 2024",
            "tag" to "Notice",
            "tagKn" to "ಸೂಚನೆ",
            "color" to "amber",
            "image" to "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400"
        ),
        mapOf(
            "title" to "Road Work Completion",
            "titleKn" to "ರಸ್ತೆ ಕಾಮಗಾರಿ ಪೂರ್ಣ",
            "desc" to "We are happy to announce that the main road repair work in Ward 4 has been successfully completed.",
            "descKn" to "ವಾರ್ಡ್ 4 ರಲ್ಲಿ ಮುಖ್ಯ ರಸ್ತೆ ದುರಸ್ತಿ ಕಾಮಗಾರಿ ಯಶಸ್ವಿಯಾಗಿ ಪೂರ್ಣಗೊಂಡಿದೆ.",
            "date" to "May 8, 2024",
            "tag" to "Completed",
            "tagKn" to "ಪೂರ್ಣ",
            "color" to "green",
            "image" to "https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?w=400"
        ),
        mapOf(
            "title" to "Health Camp",
            "titleKn" to "ಆರೋಗ್ಯ ಶಿಬಿರ",
            "desc" to "Free health checkup camp organized by Gram Panchayat on May 15th at the Primary Health Center.",
            "descKn" to "ಮೇ 15 ರಂದು ಪ್ರಾಥಮಿಕ ಆರೋಗ್ಯ ಕೇಂದ್ರದಲ್ಲಿ ಉಚಿತ ಆರೋಗ್ಯ ತಪಾಸಣೆ ಶಿಬಿರ ಆಯೋಜಿಸಲಾಗಿದೆ.",
            "date" to "May 7, 2024",
            "tag" to "Health",
            "tagKn" to "ಆರೋಗ್ಯ",
            "color" to "green",
            "image" to "https://images.unsplash.com/photo-1559757148-5c350d0d3c56?w=400"
        ),
        mapOf(
            "title" to "Scholarship Applications",
            "titleKn" to "ವಿದ್ಯಾರ್ಥಿ ವೇತನ ಅರ್ಜಿ",
            "desc" to "Applications for government scholarships are now open. Eligible students can apply at the Panchayat office.",
            "descKn" to "ಸರ್ಕಾರಿ ವಿದ್ಯಾರ್ಥಿ ವೇತನಕ್ಕೆ ಅರ್ಜಿ ಸ್ವೀಕಾರ ಪ್ರಾರಂಭವಾಗಿದೆ. ಅರ್ಹ ವಿದ್ಯಾರ್ಥಿಗಳು ಪಂಚಾಯತ್ ಕಚೇರಿಯಲ್ಲಿ ಅರ್ಜಿ ಸಲ್ಲಿಸಬಹುದು.",
            "date" to "May 5, 2024",
            "tag" to "Education",
            "tagKn" to "ಶಿಕ್ಷಣ",
            "color" to "blue",
            "image" to "https://images.unsplash.com/photo-1580582932707-520aed937b7b?w=400"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಪ್ರಕಟಣೆಗಳು" else "Announcements",
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(announcements.size) { index ->
                val item = announcements[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = item["image"],
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(
                                            when (item["color"]) {
                                                "green" -> Color(0xFFE8F5EC)
                                                "amber" -> Color(0xFFFEF3E2)
                                                else -> Color(0xFFE8F0FB)
                                            }
                                        )
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        if (isKannada) item["tagKn"] ?: ""
                                        else item["tag"] ?: "",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = when (item["color"]) {
                                            "green" -> Color(0xFF2D7A40)
                                            "amber" -> Color(0xFFB45309)
                                            else -> Color(0xFF1A56B0)
                                        }
                                    )
                                }
                                Text(
                                    item["date"] ?: "",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(
                                if (isKannada) item["titleKn"] ?: "" else item["title"] ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                if (isKannada) item["descKn"] ?: "" else item["desc"] ?: "",
                                fontSize = 13.sp,
                                color = Color.Gray,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}