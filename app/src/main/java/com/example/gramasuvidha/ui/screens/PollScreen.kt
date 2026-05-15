package com.example.gramasuvidha.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.gramasuvidha.ui.theme.NavyBlue

data class Poll(
    val question: String,
    val questionKn: String,
    val options: List<String>,
    val optionsKn: List<String>,
    val votes: MutableList<Int>,
    val daysLeft: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PollScreen() {
    val isKannada = LanguageState.isKannada

    val polls = remember {
        mutableStateListOf(
            Poll(
                "Which project should be prioritized next?",
                "ಮುಂದೆ ಯಾವ ಯೋಜನೆಗೆ ಆದ್ಯತೆ ನೀಡಬೇಕು?",
                listOf("New Park", "Library", "Sports Ground", "Medical Center"),
                listOf("ಹೊಸ ಉದ್ಯಾನ", "ಗ್ರಂಥಾಲಯ", "ಕ್ರೀಡಾಂಗಣ", "ವೈದ್ಯಕೀಯ ಕೇಂದ್ರ"),
                mutableListOf(45, 23, 67, 38),
                3
            ),
            Poll(
                "Are you satisfied with road repair quality?",
                "ರಸ್ತೆ ದುರಸ್ತಿ ಗುಣಮಟ್ಟದಿಂದ ತೃಪ್ತರಾಗಿದ್ದೀರಾ?",
                listOf("Very Satisfied", "Satisfied", "Neutral", "Not Satisfied"),
                listOf("ಅತ್ಯಂತ ತೃಪ್ತ", "ತೃಪ್ತ", "ತಟಸ್ಥ", "ತೃಪ್ತಿಯಿಲ್ಲ"),
                mutableListOf(89, 134, 45, 22),
                7
            ),
            Poll(
                "Best time for Gram Sabha meeting?",
                "ಗ್ರಾಮಸಭೆ ಸಭೆಗೆ ಉತ್ತಮ ಸಮಯ ಯಾವುದು?",
                listOf("Morning 9 AM", "Afternoon 2 PM", "Evening 5 PM", "Sunday Morning"),
                listOf("ಬೆಳಿಗ್ಗೆ 9 ಗಂಟೆ", "ಮಧ್ಯಾಹ್ನ 2 ಗಂಟೆ", "ಸಂಜೆ 5 ಗಂಟೆ", "ಭಾನುವಾರ ಬೆಳಿಗ್ಗೆ"),
                mutableListOf(56, 34, 78, 112),
                12
            )
        )
    }

    val selectedOptions = remember { mutableStateMapOf<Int, Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    if (isKannada) "ನಾಗರಿಕ ಮತದಾನ" else "Citizen Polls",
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(polls.size) { pollIndex ->
                val poll = polls[pollIndex]
                val totalVotes = poll.votes.sum()
                val selected = selectedOptions[pollIndex]

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFFE8F0FB))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    "🗳 ${if (isKannada) "ಮತದಾನ" else "Poll"}",
                                    fontSize = 11.sp,
                                    color = NavyBlue,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Text(
                                "${poll.daysLeft} ${if (isKannada) "ದಿನ ಬಾಕಿ" else "days left"}",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(Modifier.height(10.dp))

                        Text(
                            if (isKannada) poll.questionKn else poll.question,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            lineHeight = 20.sp
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            "$totalVotes ${if (isKannada) "ಮತಗಳು" else "votes"}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(Modifier.height(12.dp))

                        // Options
                        poll.options.forEachIndexed { optIndex, option ->
                            val isSelected = selected == optIndex
                            val hasVoted = selected != null
                            val votePercent = if (totalVotes > 0)
                                (poll.votes[optIndex].toFloat() / totalVotes * 100).toInt()
                            else 0

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (isSelected) NavyBlue.copy(alpha = 0.1f)
                                        else Color(0xFFF8F9FA)
                                    )
                                    .clickable {
                                        if (selected == null) {
                                            selectedOptions[pollIndex] = optIndex
                                            polls[pollIndex].votes[optIndex]++
                                        }
                                    }
                            ) {
                                // Progress background
                                if (hasVoted) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(votePercent / 100f)
                                            .height(44.dp)
                                            .background(
                                                if (isSelected) NavyBlue.copy(alpha = 0.15f)
                                                else Color(0xFFE8F0FB)
                                            )
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(
                                            selected = isSelected,
                                            onClick = null,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = NavyBlue
                                            )
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Text(
                                            if (isKannada) poll.optionsKn[optIndex] else option,
                                            fontSize = 13.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold
                                            else FontWeight.Normal
                                        )
                                    }
                                    if (hasVoted) {
                                        Text(
                                            "$votePercent%",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) NavyBlue else Color.Gray
                                        )
                                    }
                                }
                            }
                        }

                        if (selected != null) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "✅ ${if (isKannada) "ನಿಮ್ಮ ಮತ ದಾಖಲಾಗಿದೆ" else "Your vote has been recorded"}",
                                color = Color(0xFF2D7A40),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}