package com.example.gramasuvidha.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.DarkBlue
import com.example.gramasuvidha.ui.theme.NavyBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    val isKannada = LanguageState.isKannada
    val scope = rememberCoroutineScope()

    var selectedTab by remember { mutableStateOf(0) }

    // OTP states
    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }

    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf("") }

    var showOtp by remember { mutableStateOf(false) }
    var otpSending by remember { mutableStateOf(false) }
    var otpVerifying by remember { mutableStateOf(false) }

    // Password states
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginLoading by remember { mutableStateOf(false) }

    // Google state
    var googleLoading by remember { mutableStateOf(false) }

    // Feedback
    var feedbackMsg by remember { mutableStateOf("") }
    var feedbackIsError by remember { mutableStateOf(false) }

    fun showFeedback(msg: String, isError: Boolean = false) {
        feedbackMsg = msg
        feedbackIsError = isError

        scope.launch {
            delay(3000)
            feedbackMsg = ""
        }
    }

    fun sendOtp() {

        if (phone.length != 10) {
            phoneError = "Enter valid 10 digit number"
            return
        }

        phoneError = ""

        scope.launch {

            otpSending = true

            showFeedback("Sending OTP...")

            delay(1500)

            otpSending = false
            showOtp = true

            showFeedback("OTP Sent Successfully")
        }
    }

    fun verifyOtp() {

        if (otp.length != 6) {
            otpError = "Enter valid OTP"
            return
        }

        otpError = ""

        scope.launch {

            otpVerifying = true

            showFeedback("Verifying OTP...")

            delay(1500)

            otpVerifying = false

            showFeedback("Login Successful")

            delay(1000)

            onLoginSuccess()
        }
    }

    fun passwordLogin() {

        if (username.isEmpty() || password.length < 6) {

            showFeedback(
                "Invalid Username or Password",
                true
            )

            return
        }

        scope.launch {

            loginLoading = true

            showFeedback("Logging in...")

            delay(1800)

            loginLoading = false

            showFeedback("Welcome $username")

            delay(1000)

            onLoginSuccess()
        }
    }

    fun googleLogin() {

        scope.launch {

            googleLoading = true

            showFeedback("Signing with Google...")

            delay(2000)

            googleLoading = false

            showFeedback("Google Login Successful")

            delay(1000)

            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NavyBlue,
                        DarkBlue,
                        Color(0xFF2196F3)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color.White.copy(alpha = 0.15f),
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🏘",
                    fontSize = 50.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Grama Suvidha",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (isKannada)
                    "ಗ್ರಾಮ ಡಿಜಿಟಲ್ ಪೋರ್ಟಲ್"
                else
                    "Village Digital Portal",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Feedback Card
            AnimatedVisibility(
                visible = feedbackMsg.isNotEmpty(),
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (feedbackIsError)
                            Color(0xFFFFEBEE)
                        else
                            Color(0xFFE8F5E9)
                    )
                ) {

                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = if (feedbackIsError)
                                Icons.Default.Error
                            else
                                Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = if (feedbackIsError)
                                Color.Red
                            else
                                Color(0xFF2D7A40)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = feedbackMsg,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Main Login Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = if (isKannada)
                            "ಲಾಗಿನ್ ಮಾಡಿ"
                        else
                            "Login",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyBlue
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tabs
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFFF0F4FF),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(4.dp)
                    ) {

                        listOf(
                            "📱 OTP",
                            "🔑 Password",
                            "G Google"
                        ).forEachIndexed { index, title ->

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        if (selectedTab == index)
                                            NavyBlue
                                        else
                                            Color.Transparent,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        selectedTab = index
                                    }
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = title,
                                    color = if (selectedTab == index)
                                        Color.White
                                    else
                                        Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // OTP TAB
                    if (selectedTab == 0) {

                        if (!showOtp) {

                            OutlinedTextField(
                                value = phone,
                                onValueChange = {
                                    if (it.length <= 10 && it.all { c -> c.isDigit() }) {
                                        phone = it
                                    }
                                },
                                label = {
                                    Text("Mobile Number")
                                },
                                leadingIcon = {
                                    Text(
                                        text = "+91",
                                        color = NavyBlue,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                isError = phoneError.isNotEmpty(),
                                supportingText = {
                                    if (phoneError.isNotEmpty()) {
                                        Text(phoneError)
                                    }
                                },
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = { sendOtp() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NavyBlue
                                )
                            ) {

                                if (otpSending) {

                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                }

                                Text(
                                    text = if (otpSending)
                                        "Sending..."
                                    else
                                        "Send OTP",
                                    fontSize = 16.sp
                                )
                            }

                        } else {

                            OutlinedTextField(
                                value = otp,
                                onValueChange = {
                                    if (it.length <= 6 && it.all { c -> c.isDigit() }) {
                                        otp = it
                                    }
                                },
                                label = {
                                    Text("Enter OTP")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.NumberPassword
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                isError = otpError.isNotEmpty(),
                                supportingText = {
                                    if (otpError.isNotEmpty()) {
                                        Text(otpError)
                                    }
                                },
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = { verifyOtp() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NavyBlue
                                )
                            ) {

                                if (otpVerifying) {

                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                }

                                Text(
                                    text = "Verify OTP",
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    // PASSWORD TAB
                    if (selectedTab == 1) {

                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            label = {
                                Text("Username")
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Person, null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = {
                                Text("Password")
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Lock, null)
                            },
                            trailingIcon = {

                                IconButton(
                                    onClick = {
                                        passwordVisible = !passwordVisible
                                    }
                                ) {

                                    Icon(
                                        imageVector = if (passwordVisible)
                                            Icons.Default.Visibility
                                        else
                                            Icons.Default.VisibilityOff,
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { passwordLogin() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NavyBlue
                            )
                        ) {

                            if (loginLoading) {

                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )

                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            Text(
                                text = "Login",
                                fontSize = 16.sp
                            )
                        }
                    }

                    // GOOGLE TAB
                    if (selectedTab == 2) {

                        OutlinedButton(
                            onClick = { googleLogin() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            border = BorderStroke(
                                1.dp,
                                Color.LightGray
                            )
                        ) {

                            if (googleLoading) {

                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color(0xFF4285F4),
                                    strokeWidth = 2.dp
                                )

                            } else {

                                Text(
                                    text = "G",
                                    color = Color(0xFF4285F4),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = if (googleLoading)
                                    "Signing in..."
                                else
                                    "Continue with Google",
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Gram Panchayat Digital Service",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

private val AMBER = Color(0xFFB45309)