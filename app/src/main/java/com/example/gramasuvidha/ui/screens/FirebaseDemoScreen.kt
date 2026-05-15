package com.example.gramasuvidha.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.NavyBlue
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseDemoScreen(onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada

    // Simulate Firebase loading
    var isLoading by remember { mutableStateOf(true) }
    var syncStatus by remember { mutableStateOf("Connecting to Firebase...") }
    var dotsCount by remember { mutableStateOf(0) }
    var lastSynced by remember { mutableStateOf("") }
    var liveProjectCount by remember { mutableStateOf(0) }
    var liveUserCount by remember { mutableStateOf(0) }
    var liveComplaintCount by remember { mutableStateOf(0) }

    // Simulate real-time Firebase connection
    LaunchedEffect(true) {
        syncStatus = if (isKannada) "Firebase ಗೆ ಸಂಪರ್ಕಿಸಲಾಗುತ್ತಿದೆ..." else "Connecting to Firebase..."
        delay(800)
        syncStatus = if (isKannada) "ದತ್ತಾಂಶ ಲೋಡ್ ಆಗುತ್ತಿದೆ..." else "Loading data..."
        delay(800)
        syncStatus = if (isKannada) "ರಿಯಲ್-ಟೈಮ್ ಸಿಂಕ್ ಸಕ್ರಿಯ" else "Real-time sync active ✓"
        lastSynced = "Just now"
        isLoading = false

        // Simulate live counter updates
        repeat(12) {
            liveProjectCount = it + 1
            delay(100)
        }
        repeat(248) {
            liveUserCount = it + 1
            delay(5)
        }
        repeat(23) {
            liveComplaintCount = it + 1
            delay(50)
        }
    }

    // Blinking dot animation
    LaunchedEffect(true) {
        while (true) {
            dotsCount = (dotsCount + 1) % 4
            delay(500)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        if (isKannada) "Firebase ಲೈವ್ ಡೇಟಾ" else "Firebase Live Data",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(8.dp))
                    if (!isLoading) {
                        // Live green dot
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50))
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFF6F00) // Firebase orange color
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Firebase Connection Status Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isLoading) Color(0xFFFFF8E1)
                        else Color(0xFFE8F5E9)
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color(0xFFFF6F00),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                Icons.Default.CheckCircle,
                                null,
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                syncStatus,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = if (isLoading) Color(0xFFFF6F00)
                                else Color(0xFF2D7A40)
                            )
                            if (!isLoading) {
                                Text(
                                    if (isKannada) "ಕೊನೆಯ ಸಿಂಕ್: $lastSynced"
                                    else "Last synced: $lastSynced",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            // Firebase Project Info
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🔥", fontSize = 20.sp)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Firebase Console",
                                color = Color(0xFFFF6F00),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        FirebaseInfoRow(
                            label = "Project ID",
                            value = "gramasuvidha-karnataka",
                            color = Color(0xFF4CAF50)
                        )
                        FirebaseInfoRow(
                            label = "Database",
                            value = "Firestore (Real-time)",
                            color = Color(0xFF2196F3)
                        )
                        FirebaseInfoRow(
                            label = "Storage",
                            value = "Firebase Storage",
                            color = Color(0xFFFF9800)
                        )
                        FirebaseInfoRow(
                            label = "Auth",
                            value = "Phone + Google OAuth",
                            color = Color(0xFF9C27B0)
                        )
                        FirebaseInfoRow(
                            label = "Status",
                            value = "● LIVE",
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
            }

            // Live Stats
            item {
                Text(
                    if (isKannada) "📊 ರಿಯಲ್-ಟೈಮ್ ಅಂಕಿಅಂಶಗಳು"
                    else "📊 Real-time Statistics",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LiveStatCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Folder,
                        value = "$liveProjectCount",
                        label = if (isKannada) "ಯೋಜನೆಗಳು" else "Projects",
                        color = NavyBlue,
                        bgColor = Color(0xFFE8F0FB)
                    )
                    LiveStatCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.People,
                        value = "$liveUserCount",
                        label = if (isKannada) "ಬಳಕೆದಾರರು" else "Users",
                        color = Color(0xFF2D7A40),
                        bgColor = Color(0xFFE8F5EC)
                    )
                    LiveStatCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.ReportProblem,
                        value = "$liveComplaintCount",
                        label = if (isKannada) "ದೂರುಗಳು" else "Complaints",
                        color = Color(0xFFCC2222),
                        bgColor = Color(0xFFFFEBEE)
                    )
                }
            }

            // Firestore Collections
            item {
                Text(
                    if (isKannada) "📁 Firestore ಸಂಗ್ರಹಗಳು"
                    else "📁 Firestore Collections",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(8.dp))

                val collections = listOf(
                    Triple("projects", "12 documents", Color(0xFF1A56B0)),
                    Triple("users", "248 documents", Color(0xFF2D7A40)),
                    Triple("complaints", "23 documents", Color(0xFFCC2222)),
                    Triple("announcements", "5 documents", Color(0xFFB45309)),
                    Triple("polls", "3 documents", Color(0xFF7B1FA2)),
                    Triple("feedback", "67 documents", Color(0xFF0288D1))
                )

                collections.forEach { (name, count, color) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
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
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(color.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("📄", fontSize = 16.sp)
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    name,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = color
                                )
                                Text(
                                    count,
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                            // Live indicator
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4CAF50))
                            )
                        }
                    }
                }
            }

            // Live Activity Feed
            item {
                Text(
                    if (isKannada) "⚡ ನೇರ ಚಟುವಟಿಕೆ" else "⚡ Live Activity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(8.dp))

                val activities = listOf(
                    Triple("User logged in from Ward 4", "2s ago", Color(0xFF4CAF50)),
                    Triple("New complaint filed: Road issue", "15s ago", Color(0xFFCC2222)),
                    Triple("Project updated: 85% complete", "1m ago", Color(0xFF1A56B0)),
                    Triple("Poll vote recorded", "3m ago", Color(0xFF7B1FA2)),
                    Triple("Feedback submitted: ⭐⭐⭐⭐⭐", "5m ago", Color(0xFFFF9800)),
                    Triple("New user registered", "8m ago", Color(0xFF2D7A40))
                )

                activities.forEach { (activity, time, color) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
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
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(color)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                activity,
                                modifier = Modifier.weight(1f),
                                fontSize = 13.sp
                            )
                            Text(
                                time,
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Firebase Security Rules
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "🔒 ${if (isKannada) "ಸुरक्षा ನಿಯಮಗಳು" else "Security Rules"}",
                            color = Color(0xFFFF6F00),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            """rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /projects/{projectId} {
      allow read: if request.auth != null;
      allow write: if request.auth.token.admin == true;
    }
    match /complaints/{complaintId} {
      allow read, write: if request.auth != null;
    }
    match /users/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
  }
}""",
                            color = Color(0xFF4CAF50),
                            fontSize = 10.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                        )
                    }
                }
            }

            // Performance Metrics
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (isKannada) "⚡ ಕಾರ್ಯಕ್ಷಮತೆ"
                            else "⚡ Performance Metrics",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))

                        listOf(
                            Triple(
                                if (isKannada) "ಡೇಟಾ ಲೋಡ್ ಸಮಯ" else "Data Load Time",
                                "142ms", 0.14f
                            ),
                            Triple(
                                if (isKannada) "ಸಿಂಕ್ ವೇಗ" else "Sync Speed",
                                "98ms", 0.09f
                            ),
                            Triple(
                                if (isKannada) "ಸಂಗ್ರಹ ದಕ್ಷತೆ" else "Cache Efficiency",
                                "94%", 0.94f
                            ),
                            Triple(
                                if (isKannada) "ಅಪ್ಟೈಮ್" else "Uptime",
                                "99.9%", 0.99f
                            )
                        ).forEach { (label, value, progress) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    label,
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.width(130.dp)
                                )
                                LinearProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp)),
                                    color = Color(0xFF4CAF50),
                                    trackColor = Color(0xFFE8F5E9)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    value,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4CAF50)
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun FirebaseInfoRow(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 12.sp)
        Text(value, color = color, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun LiveStatCard(
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
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(Modifier.height(4.dp))
            Text(
                value,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = color
            )
            Text(label, fontSize = 10.sp, color = color.copy(alpha = 0.7f))
        }
    }
}