package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit,
    onComplaintClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onBudgetClick: () -> Unit = {},
    onFirebaseClick: () -> Unit = {},
    onMapClick: () -> Unit = {},
    onCameraClick: () -> Unit = {}
) {
    val isKannada = LanguageState.isKannada

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
    ) {
        // Profile Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(NavyBlue, Color(0xFF1A56B0))
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 40.sp)
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "Rajesh Kumar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    if (isKannada) "ಮಡುವೆಲೂರು ಗ್ರಾಮ" else "Maduvelu Village",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        if (isKannada) "✓ ಪರಿಶೀಲಿಸಿದ ನಿವಾಸಿ"
                        else "✓ Verified Resident",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Stats Row
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(
                    value = "5",
                    label = if (isKannada) "ದೂರುಗಳು" else "Complaints"
                )
                VerticalDivider()
                ProfileStat(
                    value = "12",
                    label = if (isKannada) "ಮತಗಳು" else "Votes Cast"
                )
                VerticalDivider()
                ProfileStat(
                    value = "8",
                    label = if (isKannada) "ಪ್ರತಿಕ್ರಿಯೆ" else "Feedbacks"
                )
            }
        }

        // Menu Options
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                ProfileMenuItem(
                    icon = Icons.Default.Notifications,
                    label = if (isKannada) "ಅಧಿಸೂಚನೆಗಳು" else "Notifications",
                    color = NavyBlue,
                    onClick = onNotificationClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.ReportProblem,
                    label = if (isKannada) "ದೂರು ಸಲ್ಲಿಸಿ" else "File Complaint",
                    color = Color(0xFFCC2222),
                    onClick = onComplaintClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.AccountBalance,
                    label = if (isKannada) "ಬಜೆಟ್ ವಿವರ" else "Budget Tracker",
                    color = Color(0xFF2D7A40),
                    onClick = onBudgetClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.Settings,
                    label = if (isKannada) "ಸೆಟ್ಟಿಂಗ್ಸ್" else "Settings",
                    color = Color.Gray,
                    onClick = onSettingsClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.Info,
                    label = if (isKannada) "ಸಹಾಯ & ಬೆಂಬಲ" else "Help & Support",
                    color = Color(0xFF1A56B0),
                    onClick = {}
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.Cloud,
                    label = if (isKannada) "🔥 Firebase ಲೈವ್ ಡೇಟಾ" else "🔥 Firebase Live Data",
                    color = Color(0xFFFF6F00),
                    onClick = onFirebaseClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.Map,
                    label = if (isKannada) "📍 ಯೋಜನೆ ನಕ್ಷೆ" else "📍 Projects Map",
                    color = Color(0xFF1A56B0),
                    onClick = onMapClick
                )
                HorizontalDivider(color = Color(0xFFF5F5F5))
                ProfileMenuItem(
                    icon = Icons.Default.PhotoCamera,
                    label = if (isKannada) "📸 ಫೋಟೋ ಅಪ್ಲೋಡ್" else "📸 Upload Photos",
                    color = Color(0xFF7B1FA2),
                    onClick = onCameraClick
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Panchayat Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    if (isKannada) "ಪಂಚಾಯತ್ ಮಾಹಿತಿ" else "Panchayat Info",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = NavyBlue
                )
                Spacer(Modifier.height(10.dp))
                InfoRow(
                    label = if (isKannada) "ಅಧ್ಯಕ್ಷ" else "President",
                    value = "Smt. Lakshmi Devi"
                )
                InfoRow(
                    label = if (isKannada) "ಪಿಡಿಒ" else "PDO",
                    value = "Sri. Mohan Rao"
                )
                InfoRow(
                    label = if (isKannada) "ಫೋನ್" else "Phone",
                    value = "+91 98765 43210"
                )
                InfoRow(
                    label = if (isKannada) "ಇಮೇಲ್" else "Email",
                    value = "gp.maduvelu@karnataka.gov.in"
                )
                InfoRow(
                    label = if (isKannada) "ಕಚೇರಿ ಸಮಯ" else "Office Hours",
                    value = if (isKannada) "ಸೋಮ-ಶನಿ 10AM-5PM"
                    else "Mon-Sat 10AM-5PM"
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Logout Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2222))
        ) {
            Icon(Icons.Default.Logout, null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text(
                if (isKannada) "ಲಾಗ್ ಔಟ್" else "Logout",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = NavyBlue)
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Icon(
            Icons.Default.ChevronRight,
            null,
            tint = Color.LightGray,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 13.sp)
        Text(value, fontWeight = FontWeight.Medium, fontSize = 13.sp)
    }
}

@Composable
fun VerticalDivider() {
    HorizontalDivider(
        modifier = Modifier
            .height(40.dp)
            .width(1.dp),
        color = Color.LightGray
    )
}