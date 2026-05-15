package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
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
import com.example.gramasuvidha.ui.theme.NavyBlue

data class NotificationItem(
    val title: String,
    val titleKannada: String,
    val message: String,
    val messageKannada: String,
    val time: String,
    val isUnread: Boolean,
    val type: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada

    val notifications = listOf(
        NotificationItem(
            "Road Repair Update",
            "ರಸ್ತೆ ದುರಸ್ತಿ ಅಪ್‌ಡೇಟ್",
            "Ward 4 road repair is now 85% complete",
            "ವಾರ್ಡ್ 4 ರಸ್ತೆ ದುರಸ್ತಿ 85% ಪೂರ್ಣವಾಗಿದೆ",
            "2 hours ago", true, "update"
        ),
        NotificationItem(
            "New Project Started",
            "ಹೊಸ ಯೋಜನೆ ಪ್ರಾರಂಭ",
            "Solar power project has been initiated",
            "ಸೌರ ವಿದ್ಯುತ್ ಯೋಜನೆ ಪ್ರಾರಂಭಿಸಲಾಗಿದೆ",
            "5 hours ago", true, "new"
        ),
        NotificationItem(
            "Complaint Resolved",
            "ದೂರು ಪರಿಹಾರ",
            "Your complaint #GS2024001 has been resolved",
            "ನಿಮ್ಮ ದೂರು #GS2024001 ಪರಿಹರಿಸಲಾಗಿದೆ",
            "1 day ago", true, "resolved"
        ),
        NotificationItem(
            "Community Meeting",
            "ಸಮುದಾಯ ಸಭೆ",
            "Gram Sabha meeting scheduled for Sunday",
            "ಭಾನುವಾರ ಗ್ರಾಮಸಭೆ ನಿಗದಿಯಾಗಿದೆ",
            "2 days ago", false, "meeting"
        ),
        NotificationItem(
            "Water Supply Restored",
            "ನೀರು ಸರಬರಾಜು ಪುನಃಸ್ಥಾಪನೆ",
            "Water supply has been restored in Ward 2",
            "ವಾರ್ಡ್ 2 ರಲ್ಲಿ ನೀರು ಸರಬರಾಜು ಪುನಃಸ್ಥಾಪಿಸಲಾಗಿದೆ",
            "3 days ago", false, "update"
        ),
        NotificationItem(
            "New Poll Available",
            "ಹೊಸ ಮತದಾನ",
            "Vote on the new park development project",
            "ಹೊಸ ಉದ್ಯಾನ ಅಭಿವೃದ್ಧಿ ಯೋಜನೆಗೆ ಮತ ನೀಡಿ",
            "4 days ago", false, "poll"
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
                    if (isKannada) "ಅಧಿಸೂಚನೆಗಳು" else "Notifications",
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(notifications) { _, notif ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (notif.isUnread) Color(0xFFF0F4FF)
                        else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Icon
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    when (notif.type) {
                                        "update" -> Color(0xFFE8F0FB)
                                        "new" -> Color(0xFFE8F5EC)
                                        "resolved" -> Color(0xFFE8F5EC)
                                        "meeting" -> Color(0xFFFEF3E2)
                                        "poll" -> Color(0xFFF3E8FB)
                                        else -> Color(0xFFF0F4FF)
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                when (notif.type) {
                                    "update" -> "📋"
                                    "new" -> "🆕"
                                    "resolved" -> "✅"
                                    "meeting" -> "👥"
                                    "poll" -> "🗳"
                                    else -> "🔔"
                                },
                                fontSize = 18.sp
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                if (isKannada) notif.titleKannada else notif.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(Modifier.height(3.dp))
                            Text(
                                if (isKannada) notif.messageKannada else notif.message,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                lineHeight = 16.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                notif.time,
                                fontSize = 11.sp,
                                color = Color.LightGray
                            )
                        }
                        if (notif.isUnread) {
                            Icon(
                                Icons.Default.Circle,
                                contentDescription = null,
                                tint = NavyBlue,
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}