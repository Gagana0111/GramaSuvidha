package com.example.gramasuvidha.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.ui.theme.NavyBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {

    val scale = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }
    val dotScale1 = remember { Animatable(1f) }
    val dotScale2 = remember { Animatable(1f) }
    val dotScale3 = remember { Animatable(1f) }

    LaunchedEffect(true) {
        // Logo animation
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        // Text fade in
        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )

        // Loading dots animation
        repeat(3) {
            dotScale1.animateTo(1.5f, tween(200))
            dotScale1.animateTo(1f, tween(200))
            dotScale2.animateTo(1.5f, tween(200))
            dotScale2.animateTo(1f, tween(200))
            dotScale3.animateTo(1.5f, tween(200))
            dotScale3.animateTo(1f, tween(200))
        }

        delay(500)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NavyBlue,
                        Color(0xFF1A56B0),
                        Color(0xFF2196F3)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated Logo
            Box(
                modifier = Modifier
                    .scale(scale.value)
                    .size(120.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏘", fontSize = 60.sp)
            }

            Spacer(Modifier.height(24.dp))

            // App name with fade
            Text(
                "Grama Suvidha",
                color = Color.White.copy(alpha = textAlpha.value),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "ಗ್ರಾಮ ಡಿಜಿಟಲ್ ಪೋರ್ಟಲ್",
                color = Color.White.copy(alpha = textAlpha.value * 0.8f),
                fontSize = 16.sp
            )

            Spacer(Modifier.height(60.dp))

            // Loading dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(dotScale1, dotScale2, dotScale3).forEach { dot ->
                    Box(
                        modifier = Modifier
                            .scale(dot.value)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.8f))
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "ಲೋಡ್ ಆಗುತ್ತಿದೆ...",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }

        // Bottom text
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Powered by",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 11.sp
            )
            Text(
                "Karnataka Gram Panchayat",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}