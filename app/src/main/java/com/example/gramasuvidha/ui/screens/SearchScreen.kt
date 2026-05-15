package com.example.gramasuvidha.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.gramasuvidha.data.Project
import com.example.gramasuvidha.data.sampleProjects
import com.example.gramasuvidha.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onProjectClick: (Int) -> Unit
) {
    val isKannada = LanguageState.isKannada
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("all") }
    var sortBy by remember { mutableStateOf("name") }
    var showFilters by remember { mutableStateOf(false) }

    val statusFilters = listOf(
        "all" to (if (isKannada) "ಎಲ್ಲಾ" else "All"),
        "inprogress" to (if (isKannada) "ಪ್ರಗತಿಯಲ್ಲಿ" else "In Progress"),
        "completed" to (if (isKannada) "ಪೂರ್ಣ" else "Completed"),
        "planned" to (if (isKannada) "ಯೋಜನೆ" else "Planned")
    )

    val filteredProjects = sampleProjects
        .filter { project ->
            val matchesSearch = if (searchQuery.isEmpty()) true
            else project.titleKannada.contains(searchQuery, ignoreCase = true) ||
                    project.titleEnglish.contains(searchQuery, ignoreCase = true) ||
                    project.locationKannada.contains(searchQuery, ignoreCase = true) ||
                    project.locationEnglish.contains(searchQuery, ignoreCase = true)
            val matchesStatus = selectedStatus == "all" || project.status == selectedStatus
            matchesSearch && matchesStatus
        }
        .sortedWith(
            when (sortBy) {
                "progress_high" -> compareByDescending { it.progress }
                "progress_low" -> compareBy { it.progress }
                "budget_high" -> compareByDescending {
                    it.budget.replace("₹", "").replace(",", "").toLong()
                }
                else -> compareBy { it.titleEnglish }
            }
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಹುಡುಕಿ" else "Search",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = { showFilters = !showFilters }) {
                    Icon(
                        Icons.Default.FilterList,
                        null,
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        // Search Bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        if (isKannada) "ಯೋಜನೆ ಹೆಸರು ಅಥವಾ ಸ್ಥಳ ಹುಡುಕಿ..."
                        else "Search by project name or location..."
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, null, tint = NavyBlue)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, null, tint = Color.Gray)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NavyBlue,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }

        // Filters Section
        AnimatedVisibility(
            visible = showFilters,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    // Status Filter
                    Text(
                        if (isKannada) "ಸ್ಥಿತಿ" else "Status",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = NavyBlue
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        statusFilters.forEach { (key, label) ->
                            FilterChip(
                                selected = selectedStatus == key,
                                onClick = { selectedStatus = key },
                                label = { Text(label, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NavyBlue,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    // Sort By
                    Text(
                        if (isKannada) "ವಿಂಗಡಿಸಿ" else "Sort By",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = NavyBlue
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "name" to (if (isKannada) "ಹೆಸರು" else "Name"),
                            "progress_high" to (if (isKannada) "ಹೆಚ್ಚು %" else "High %"),
                            "progress_low" to (if (isKannada) "ಕಡಿಮೆ %" else "Low %"),
                            "budget_high" to (if (isKannada) "ಬಜೆಟ್" else "Budget")
                        ).forEach { (key, label) ->
                            FilterChip(
                                selected = sortBy == key,
                                onClick = { sortBy = key },
                                label = { Text(label, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NavyBlue,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Results count
        Text(
            "${filteredProjects.size} ${if (isKannada) "ಯೋಜನೆಗಳು ಕಂಡುಬಂದಿವೆ" else "projects found"}",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(8.dp))

        // Results
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredProjects) { project ->
                SearchResultCard(
                    project = project,
                    isKannada = isKannada,
                    onClick = { onProjectClick(project.id) }
                )
            }

            if (filteredProjects.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🔍", fontSize = 48.sp)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            if (isKannada) "ಯಾವುದೇ ಯೋಜನೆ ಕಂಡುಬಂದಿಲ್ಲ"
                            else "No projects found",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            if (isKannada) "ಬೇರೆ ಪದಗಳಿಂದ ಹುಡುಕಿ"
                            else "Try different search terms",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultCard(
    project: Project,
    isKannada: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(10.dp))
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
                        project.titleKannada.contains("ಸೌರ") -> "☀"
                        project.titleKannada.contains("ಮಳೆ") -> "🌧"
                        project.titleKannada.contains("ಸಮುದಾಯ") -> "🏛"
                        project.titleKannada.contains("ಮಾರುಕಟ್ಟೆ") -> "🏪"
                        else -> "🏗"
                    },
                    fontSize = 22.sp
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    if (isKannada) project.titleKannada else project.titleEnglish,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1
                )
                Text(
                    if (isKannada) project.locationKannada else project.locationEnglish,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { project.progress / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = when (project.status) {
                        "completed" -> GreenColor
                        "inprogress" -> NavyBlue
                        else -> AmberColor
                    },
                    trackColor = Color(0xFFF0F4FF)
                )
            }

            Spacer(Modifier.width(10.dp))

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "${project.progress}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = NavyBlue
                )
                Text(
                    project.budget,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}