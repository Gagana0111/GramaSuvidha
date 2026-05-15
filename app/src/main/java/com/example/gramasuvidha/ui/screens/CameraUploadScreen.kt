package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.gramasuvidha.ui.theme.NavyBlue
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraUploadScreen(onBack: () -> Unit) {
    val isKannada = LanguageState.isKannada
    var isUploading by remember { mutableStateOf(false) }
    var uploadProgress by remember { mutableStateOf(0f) }
    var uploadComplete by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableStateOf<Int?>(null) }
    var showUploadSuccess by remember { mutableStateOf(false) }

    val uploadedPhotos = listOf(
        "https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?w=300",
        "https://images.unsplash.com/photo-1504307651254-35680f356dfd?w=300",
        "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=300",
        "https://images.unsplash.com/photo-1580582932707-520aed937b7b?w=300",
        "https://images.unsplash.com/photo-1509391366360-2e959784a276?w=300",
        "https://images.unsplash.com/photo-1497366216548-37526070297c?w=300"
    )

    LaunchedEffect(isUploading) {
        if (isUploading) {
            repeat(100) {
                uploadProgress = it / 100f
                delay(30)
            }
            uploadProgress = 1f
            isUploading = false
            uploadComplete = true
            showUploadSuccess = true
            delay(2000)
            showUploadSuccess = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ಫೋಟೋ ಅಪ್ಲೋಡ್" else "Upload Photos",
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Upload success message
            if (showUploadSuccess) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            null,
                            tint = Color(0xFF2D7A40),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            if (isKannada) "✅ Firebase Storage ಗೆ ಯಶಸ್ವಿಯಾಗಿ ಅಪ್ಲೋಡ್ ಆಗಿದೆ!"
                            else "✅ Successfully uploaded to Firebase Storage!",
                            color = Color(0xFF2D7A40),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // Camera/Gallery buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { isUploading = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)
                ) {
                    Icon(Icons.Default.PhotoCamera, null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isKannada) "ಕ್ಯಾಮರಾ" else "Camera",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedButton(
                    onClick = { isUploading = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue)
                ) {
                    Icon(Icons.Default.PhotoLibrary, null, tint = NavyBlue)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isKannada) "ಗ್ಯಾಲರಿ" else "Gallery",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Upload progress
            if (isUploading) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                if (isKannada) "Firebase Storage ಗೆ ಅಪ್ಲೋಡ್..."
                                else "Uploading to Firebase Storage...",
                                fontSize = 13.sp,
                                color = NavyBlue,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "${(uploadProgress * 100).toInt()}%",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = NavyBlue
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = { uploadProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = NavyBlue,
                            trackColor = Color(0xFFE0E7F7)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "gs://gramasuvidha.appspot.com/projects/photos/",
                            fontSize = 10.sp,
                            color = Color.Gray,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                        )
                    }
                }
            }

            // Storage info
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🔥", fontSize = 16.sp)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "Firebase Storage",
                            color = Color(0xFFFF6F00),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            if (isKannada) "ಬಳಸಿದ ಜಾಗ" else "Storage Used",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text("234 MB / 5 GB", color = Color.White, fontSize = 12.sp)
                    }
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { 0.047f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Color(0xFFFF6F00),
                        trackColor = Color.Gray.copy(alpha = 0.3f)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        if (isKannada) "4.7% ಬಳಕೆಯಾಗಿದೆ"
                        else "4.7% used",
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                }
            }

            // Uploaded photos grid
            Text(
                if (isKannada) "📸 ಅಪ್ಲೋಡ್ ಆದ ಫೋಟೋಗಳು (${uploadedPhotos.size})"
                else "📸 Uploaded Photos (${uploadedPhotos.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = NavyBlue
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(uploadedPhotos.size) { index ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = if (selectedImageIndex == index) 2.dp else 0.dp,
                                color = NavyBlue,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                selectedImageIndex =
                                    if (selectedImageIndex == index) null else index
                            }
                    ) {
                        AsyncImage(
                            model = uploadedPhotos[index],
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (selectedImageIndex == index) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(NavyBlue.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        // Storage URL tag
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color.Black.copy(alpha = 0.6f))
                                .padding(horizontal = 3.dp, vertical = 1.dp)
                        ) {
                            Text(
                                "gs://...",
                                color = Color.White,
                                fontSize = 7.sp
                            )
                        }
                    }
                }
            }
        }
    }
}