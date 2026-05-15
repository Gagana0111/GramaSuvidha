package com.example.gramasuvidha

import android.content.Context
import android.content.Intent
import com.example.gramasuvidha.data.Project

object ShareUtils {
    fun shareProject(context: Context, project: Project, isKannada: Boolean) {
        val text = if (isKannada) {
            """
🏘 ಗ್ರಾಮ ಸುವಿಧಾ - ಯೋಜನೆ ವಿವರ

📋 ಯೋಜನೆ: ${project.titleKannada}
📍 ಸ್ಥಳ: ${project.locationKannada}
💰 ಬಜೆಟ್: ${project.budget}
📊 ಪ್ರಗತಿ: ${project.progress}%
🔖 ಸ್ಥಿತಿ: ${when(project.status) {
                "inprogress" -> "ಪ್ರಗತಿಯಲ್ಲಿದೆ"
                "completed" -> "ಪೂರ್ಣಗೊಂಡಿದೆ"
                else -> "ಯೋಜಿಸಲಾಗಿದೆ"
            }}

ಗ್ರಾಮ ಸುವಿಧಾ ಅಪ್ ಡೌನ್‌ಲೋಡ್ ಮಾಡಿ ಹೆಚ್ಚಿನ ಮಾಹಿತಿ ಪಡೆಯಿರಿ!
            """.trimIndent()
        } else {
            """
🏘 Grama Suvidha - Project Details

📋 Project: ${project.titleEnglish}
📍 Location: ${project.locationEnglish}
💰 Budget: ${project.budget}
📊 Progress: ${project.progress}%
🔖 Status: ${when(project.status) {
                "inprogress" -> "In Progress"
                "completed" -> "Completed"
                else -> "Planned"
            }}

Download Grama Suvidha app for more details!
            """.trimIndent()
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Grama Suvidha - ${project.titleEnglish}")
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(Intent.createChooser(intent, "Share Project"))
    }

    fun shareReport(context: Context, isKannada: Boolean, totalProjects: Int,
                    completed: Int, inProgress: Int, planned: Int) {
        val text = if (isKannada) {
            """
📊 ಗ್ರಾಮ ಸುವಿಧಾ - ಮಾಸಿಕ ವರದಿ

🏘 ಒಟ್ಟು ಯೋಜನೆಗಳು: $totalProjects
✅ ಪೂರ್ಣಗೊಂಡಿವೆ: $completed
🔄 ಪ್ರಗತಿಯಲ್ಲಿವೆ: $inProgress
📋 ಯೋಜಿಸಲಾಗಿದೆ: $planned

ಮಡುವೆಲೂರು ಗ್ರಾಮ ಪಂಚಾಯತ್
Karnataka, India
            """.trimIndent()
        } else {
            """
📊 Grama Suvidha - Monthly Report

🏘 Total Projects: $totalProjects
✅ Completed: $completed
🔄 In Progress: $inProgress
📋 Planned: $planned

Maduvelu Gram Panchayat
Karnataka, India
            """.trimIndent()
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Grama Suvidha Monthly Report")
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(Intent.createChooser(intent, "Share Report"))
    }
}