package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.gramasuvidha.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGalleryScreen(projectTitle: String, onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada

    val photos = listOf(
        Pair("Before Work", "https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?w=400"),
        Pair("Foundation", "https://images.unsplash.com/photo-1504307651254-35680f356dfd?w=400"),
        Pair("In Progress", "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400"),
        Pair("Material", "https://images.unsplash.com/photo-1542838132-92c53300491e?w=400"),
        Pair("Workers", "https://images.unsplash.com/photo-1497366216548-37526070297c?w=400"),
        Pair("50% Done", "https://images.unsplash.com/photo-1580582932707-520aed937b7b?w=400"),
        Pair("Inspection", "https://images.unsplash.com/photo-1509391366360-2e959784a276?w=400"),
        Pair("Completion", "https://images.unsplash.com/photo-1519692933481-e162a57d6721?w=400")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಫೋಟೋ ಗ್ಯಾಲರಿ" else "Photo Gallery",
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

        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                projectTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = NavyBlue
            )
            Text(
                "${photos.size} ${if (isKannada) "ಫೋಟೋಗಳು" else "photos"}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(photos.size) { index ->
                val (caption, url) = photos[index]
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = url,
                            contentDescription = caption,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .padding(6.dp)
                        ) {
                            Text(
                                caption,
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}