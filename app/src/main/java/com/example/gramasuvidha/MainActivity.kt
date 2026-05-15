package com.example.gramasuvidha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.gramasuvidha.ui.screens.*
import com.example.gramasuvidha.ui.theme.GramaSuvidha1Theme
import com.example.gramasuvidha.ui.theme.NavyBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GramaSuvidha1Theme {
                val isKannada = LanguageState.isKannada
                AppNavigation()
            }
        }
    }
}

data class BottomNavItem(
    val label: String,
    val labelKannada: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun AppNavigation() {
    val isKannada = LanguageState.isKannada
    val navController = rememberNavController()
    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem("Home", "ಮನೆ", Icons.Default.Home, "home"),
        BottomNavItem("Dashboard", "ಡ್ಯಾಶ್‌ಬೋರ್ಡ್", Icons.Default.BarChart, "dashboard"),
        BottomNavItem("News", "ಸುದ್ದಿ", Icons.Default.Article, "announcements"),
        BottomNavItem("Polls", "ಮತ", Icons.Default.Poll, "polls"),
        BottomNavItem("Profile", "ಪ್ರೊಫೈಲ್", Icons.Default.Person, "profile")
    )

    val showBottomNav = currentRoute in listOf(
        "home", "dashboard", "announcements", "polls", "profile"
    )

    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        bottomNavItems.forEach { item ->
                            val isSelected = currentRoute == item.route
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(item.route) {
                                            popUpTo("home") { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(
                                            if (isSelected) NavyBlue.copy(alpha = 0.1f)
                                            else Color.Transparent
                                        )
                                        .padding(horizontal = 16.dp, vertical = 6.dp)
                                ) {
                                    Icon(
                                        item.icon,
                                        contentDescription = null,
                                        tint = if (isSelected) NavyBlue else Color.Gray,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Text(
                                    if (isKannada) item.labelKannada else item.label,
                                    fontSize = 10.sp,
                                    color = if (isSelected) NavyBlue else Color.Gray,
                                    fontWeight = if (isSelected) FontWeight.Bold
                                    else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("splash") {
                SplashScreen(onFinished = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                })
            }
            composable("login") {
                LoginScreen(onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                })
            }
            composable("home") {
                HomeScreen(
                    onProjectClick = { navController.navigate("detail/$it") },
                    onSettingsClick = { navController.navigate("settings") },
                    onComplaintClick = { navController.navigate("complaint") },
                    onNotificationClick = { navController.navigate("notifications") },
                    onSearchClick = { navController.navigate("search") }
                )
            }
            composable("dashboard") {
                DashboardScreen(
                    onProjectClick = { navController.navigate("detail/$it") }
                )
            }
            composable("announcements") {
                AnnouncementScreen()
            }
            composable("polls") {
                PollScreen()
            }
            composable("profile") {
                ProfileScreen(
                    onSettingsClick = { navController.navigate("settings") },
                    onComplaintClick = { navController.navigate("complaint") },
                    onNotificationClick = { navController.navigate("notifications") },
                    onBudgetClick = { navController.navigate("budget") },
                    onFirebaseClick = { navController.navigate("firebase") },
                    onMapClick = { navController.navigate("map") },
                    onCameraClick = { navController.navigate("camera") }
                )
            }
            composable(
                route = "detail/{projectId}",
                arguments = listOf(navArgument("projectId") { type = NavType.IntType })
            ) { backStackEntry ->
                val projectId = backStackEntry.arguments?.getInt("projectId") ?: 1
                ProjectDetailScreen(
                    projectId = projectId,
                    onBack = { navController.popBackStack() },
                    onGalleryClick = { title ->
                        navController.navigate("gallery/$title")
                    },
                    onBudgetClick = {
                        navController.navigate("budget")
                    }
                )
            }
            // Firebase Demo
            composable("firebase") {
                FirebaseDemoScreen(onBack = { navController.popBackStack() })
            }

// Map Demo
            composable("map") {
                MapDemoScreen(onBack = { navController.popBackStack() })
            }

// Camera Upload
            composable("camera") {
                CameraUploadScreen(onBack = { navController.popBackStack() })
            }
            composable("settings") {
                SettingsScreen(onBack = { navController.popBackStack() })
            }
            composable("complaint") {
                ComplaintScreen(onBack = { navController.popBackStack() })
            }
            composable("notifications") {
                NotificationScreen(onBack = { navController.popBackStack() })
            }
            composable("search") {
                SearchScreen(
                    onBack = { navController.popBackStack() },
                    onProjectClick = { navController.navigate("detail/$it") }
                )
            }
            composable("budget") {
                BudgetScreen(onBack = { navController.popBackStack() })
            }
            composable(
                route = "gallery/{title}",
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                PhotoGalleryScreen(
                    projectTitle = title,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}