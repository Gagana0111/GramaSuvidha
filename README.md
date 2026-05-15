# 🏘️ Grama Suvidha1 — Village Project Tracking Portal

An Android application to track, monitor, and explore gram panchayat development projects in Karnataka, India.

---

## 🚨 Problem Statement

Village panchayat projects often lack transparency. Residents have no easy way to check the progress, budget usage, or status of ongoing development work like road repairs, water supply, or school renovation. Local artisans and contractors remain unaccountable and villagers stay uninformed about public fund usage.

---

## 💡 Solution

Grama Suvidha1 provides a digital village dashboard where every panchayat project is listed with its real-time progress, budget breakdown, and status. Villagers can browse projects, read descriptions in Kannada, and submit feedback directly through the app.

---

## ✨ Features

- 📊 **Dashboard** — Live stats showing In Progress, Completed, and Planned project counts
- 🗂️ **Filter Tabs** — View All / In Progress / Completed projects with one tap
- 📋 **Project Cards** — Each card shows title in Kannada, location, progress bar, and budget
- 📄 **Project Detail Screen** — Full description, cost breakdown by category, materials used
- 💬 **Feedback Dialog** — Villagers can submit feedback with star rating and issue report toggle
- 🤖 **Gemini AI Integration** — AI-powered project summaries and village Q&A in Kannada
- 🌐 **Kannada Language UI** — Full Kannada text throughout all screens
- 🎨 **Material 3 Design** — Clean navy blue, green, and amber color scheme

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Android Studio | Development environment |
| Jetpack Compose | UI framework |
| Navigation Compose | Screen navigation |
| Google Gemini API | AI-powered project summaries and Q&A |
| Material Design 3 | UI components and theming |
| MVVM Architecture | Clean code structure |

---

## 📁 Project Structure

    app/src/main/java/com/example/gramasuvidha/
    ├── MainActivity.kt
    ├── data/
    │   └── Project.kt
    └── ui/
        ├── screens/
        │   ├── HomeScreen.kt
        │   └── ProjectDetailScreen.kt
        └── theme/
            └── Theme.kt

---

## ⚙️ Setup Instructions

### Prerequisites
- Android Studio Meerkat or later
- Android device or emulator (API 24 / Android 7.0+)
- Google Gemini API key (free at https://aistudio.google.com)

### Steps to Run

**1. Clone the repository**

    git clone https://github.com/YOUR_USERNAME/GramaSuvidha1.git
    cd GramaSuvidha1

**2. Open in Android Studio**
- Open Android Studio → File → Open → select the cloned folder

**3. Add Gemini API Key**
- Get a free key from https://aistudio.google.com
- Open local.properties and add:

    GEMINI_API_KEY=your_api_key_here

**4. Sync Gradle**
- Click Sync Now when prompted by Android Studio

**5. Run the app**
- Click the Run button in Android Studio
- Minimum SDK: Android 7.0 (API 24)

---

## 📊 Sample Projects Included

| Status | Count | Examples |
|---|---|---|
| In Progress | 6 | Road repair, Temple construction, Water supply, Drainage, Drinking water centres, Market maintenance |
| Completed | 3 | School renovation, Community hall, Street lighting |
| Planned | 3 | New road Ward 7, Solar power, Rainwater harvesting |

---

## 🔮 Future Enhancements

- Firebase Firestore integration for live data from panchayat officials
- Photo upload for real site progress updates
- Push notifications when project status changes
- English and Kannada language toggle
- Offline mode with local data caching
- Admin panel for panchayat officials to update progress
- QR code at project site to instantly open project details

---

## 👩‍💻 Developed By

**Gagana G**

USN: 1AT22CG024

Atria Institute of Technology, Bangalore

Academic Year: 2025-26

---

## 📜 License

Built for gram panchayat communities of Karnataka.
Grama Suvidha1 — Village Digital Dashboard
