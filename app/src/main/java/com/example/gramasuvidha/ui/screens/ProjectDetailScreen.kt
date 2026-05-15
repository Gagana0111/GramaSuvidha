package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
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
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.AmberColor
import com.example.gramasuvidha.ui.theme.DarkBlue
import com.example.gramasuvidha.ui.theme.GreenColor
import com.example.gramasuvidha.ui.theme.NavyBlue
import androidx.compose.ui.platform.LocalContext
import com.example.gramasuvidha.ShareUtils
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    projectId: Int,
    onBack: () -> Unit,
    onGalleryClick: (String) -> Unit = {},
    onBudgetClick: () -> Unit = {}
) {
    val project = sampleProjects.find { it.id == projectId } ?: return
    var showFeedbackDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = {
                Text(
                    if (LanguageState.isKannada) "ಯೋಜನೆ ವಿವರ" else "Project Detail",
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

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // Project Image
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
                    .height(220.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {

                // Title & Description
                Text(
                    if (LanguageState.isKannada) project.titleKannada else project.titleEnglish,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    if (LanguageState.isKannada) project.descriptionKannada
                    else project.descriptionEnglish,
                    fontSize = 14.sp,
                    color = Color(0xFF555555),
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(16.dp))

                // Progress Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ಪ್ರಗತಿ ವಿವರ" else "Progress Details",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                if (LanguageState.isKannada) "ಒಟ್ಟು ಪ್ರಗತಿ" else "Overall Progress",
                                fontSize = 13.sp, color = Color.Gray
                            )
                            Text(
                                "${project.progress}%",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = NavyBlue
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { project.progress / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            color = NavyBlue,
                            trackColor = Color(0xFFE0E7F7)
                        )
                        Spacer(Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            MiniStat(
                                label = if (LanguageState.isKannada) "ಬಜೆಟ್" else "Budget",
                                value = project.budget,
                                color = NavyBlue
                            )
                            MiniStat(
                                label = if (LanguageState.isKannada) "ಸ್ಥಿತಿ" else "Status",
                                value = when (project.status) {
                                    "inprogress" -> if (LanguageState.isKannada) "ಚಾಲ್ತಿ" else "Active"
                                    "completed" -> if (LanguageState.isKannada) "ಮುಗಿದಿದೆ" else "Done"
                                    else -> if (LanguageState.isKannada) "ಯೋಜನೆ" else "Planned"
                                },
                                color = when (project.status) {
                                    "completed" -> GreenColor
                                    "inprogress" -> DarkBlue
                                    else -> AmberColor
                                }
                            )
                            MiniStat(
                                label = if (LanguageState.isKannada) "ಉಳಿದಿದೆ" else "Remaining",
                                value = "${100 - project.progress}%",
                                color = Color(0xFFCC2222)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Cost Breakdown Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ಕೆಲಸದ ಮೊತ್ತದ ವಿವರಣೆ"
                            else "Cost Breakdown",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(8.dp))
                        val breakdown = if (LanguageState.isKannada)
                            project.costBreakdown
                        else
                            project.costBreakdownEnglish
                        breakdown.forEach { (key, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(key, fontSize = 13.sp, color = Color(0xFF333333))
                                Text(
                                    "$value%",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NavyBlue
                                )
                            }
                            LinearProgressIndicator(
                                progress = { value / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = NavyBlue,
                                trackColor = Color(0xFFE0E7F7)
                            )
                            Spacer(Modifier.height(6.dp))
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Used Items Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            if (LanguageState.isKannada) "ಬಳಸಿದ ಘಟಕಗಳು" else "Items Used",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = NavyBlue
                        )
                        Spacer(Modifier.height(8.dp))
                        val items = if (LanguageState.isKannada)
                            project.usedItems
                        else
                            project.usedItemsEnglish
                        items.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFFE8F0FB)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "${index + 1}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NavyBlue
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                                Text(item, fontSize = 13.sp, color = Color(0xFF333333))
                            }
                            Spacer(Modifier.height(4.dp))
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }

        // Gallery & Budget Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { onGalleryClick(project.titleEnglish) },
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue)
            ) {
                Text("📷 ${if (LanguageState.isKannada) "ಫೋಟೋ" else "Gallery"}")
            }
            OutlinedButton(
                onClick = { onBudgetClick() },
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue)
            ) {
                Text("💰 ${if (LanguageState.isKannada) "ಬಜೆಟ್" else "Budget"}")
            }
        }
        // Share Button
        Button(
            onClick = {
                ShareUtils.shareProject(context, project, LanguageState.isKannada)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(46.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D7A40)
            )
        ) {
            Icon(
                Icons.Default.Share,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                if (LanguageState.isKannada) "ಯೋಜನೆ ಹಂಚಿಕೊಳ್ಳಿ" else "Share Project",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(4.dp))

        // Feedback & Report Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { showFeedbackDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)
            ) {
                Text(
                    if (LanguageState.isKannada) "ಪ್ರತಿಕ್ರಿಯೆ ನೀಡಿ" else "Give Feedback",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCC2222))
            ) {
                Text(
                    if (LanguageState.isKannada) "ಸಮಸ್ಯೆ ವರದಿ ಮಾಡಿ" else "Report Issue",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }

    if (showFeedbackDialog) {
        FeedbackDialog(onDismiss = { showFeedbackDialog = false })
    }
}

@Composable
fun MiniStat(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = color)
        Text(label, fontSize = 11.sp, color = Color.Gray)
    }
}

@Composable
fun FeedbackDialog(onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    var isReportIssue by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(3) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    if (LanguageState.isKannada) "ಪ್ರತಿಕ್ರಿಯೆಯನ್ನು ಸಲ್ಲಿಸಿ"
                    else "Submit Feedback",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(if (LanguageState.isKannada) "ನಿಮ್ಮ ಹೆಸರು" else "Your Name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (LanguageState.isKannada) "ಸಮಸ್ಯೆ ವರದಿ ಮಾಡಿ" else "Report Issue",
                        color = Color(0xFFCC2222),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(checked = isReportIssue, onCheckedChange = { isReportIssue = it })
                }
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    placeholder = {
                        Text(
                            if (LanguageState.isKannada) "ನಿಮ್ಮ ಪ್ರತಿಕ್ರಿಯೆ" else "Your feedback"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 4
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    if (LanguageState.isKannada) "ರೇಟಿಂಗ್" else "Rating",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = NavyBlue,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = if (i <= rating) Icons.Filled.Star
                            else Icons.Outlined.StarOutline,
                            contentDescription = null,
                            tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { rating = i }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)
                ) {
                    Text(
                        if (LanguageState.isKannada) "ಸಲ್ಲಿಸು" else "Submit",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}