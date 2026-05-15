package com.example.gramasuvidha.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NavyBlue = Color(0xFF1A3A6B)
val DarkBlue = Color(0xFF1A56B0)
val GreenColor = Color(0xFF2D7A40)
val AmberColor = Color(0xFFB45309)
val RedColor = Color(0xFFCC2222)
val LightBlue = Color(0xFFE8F0FB)
val LightGreen = Color(0xFFE8F5EC)
val LightAmber = Color(0xFFFEF3E2)

private val ColorScheme = lightColorScheme(
    primary = NavyBlue,
    secondary = DarkBlue,
    background = Color(0xFFF5F5F5),
    surface = Color.White
)

@Composable
fun GramaSuvidha1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        content = content
    )
}