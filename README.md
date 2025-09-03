# Pregnancy Vitals Tracker

An Android app to log and track pregnancy-related vitals, including blood pressure, heart rate, weight, and baby kicks. Users can add new entries, view a live-updating list of their vitals, and run a background timer service.

---

## 🎨 Design

The app design is available on Figma:  
[Figma Design Link](https://www.figma.com/design/m0fkRHhJOhyACg7pydmghA/Android-SDE-Assignment?node-id=0-3&t=L0xntkR63V3Zpxol-0)

---

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Dependency Injection:** Hilt
- **Database:** Room
- **State Management:** StateFlow / LiveData
- **Background Tasks:** Android Service + Coroutines

---

## 📱 Features

### Part I – Vitals Tracking
- Add pregnancy vitals:
  - Blood Pressure (Systolic / Diastolic)
  - Heart Rate
  - Weight
  - Baby Kicks Count
- Live-updating list of vitals using `LazyColumn`
- Data persistence using **Room Database**

### Part II – Background Timer
- Single “Start” button triggers a background timer
- Emits current time every second to the UI
- Runs in background, even if the app is minimized or killed
- Communicates via proper channels (StateFlow / BroadcastReceiver), no static variables

---

## 🏗 Architecture & Modules

- **MVVM Pattern**
  - **ViewModels** manage UI state via `StateFlow` or `LiveData`
  - **Repositories** handle all data operations
  - **Room DAO** handles database queries
- **Hilt DI** for dependency injection
- **Utilities**: Constants, dimensions, colors, and theme management

## 📂 Folder Structure

data/
├─ local/
│  ├─ Vital.kt               # Room entity
│  ├─ VitalDao.kt            # DAO interface
│  └─ VitalsDatabase.kt      # Room Database
├─ repository/
│  └─ VitalsRepository.kt
└─ di/
└─ AppModule.kt           # Hilt DI module

service/
└─ TimeService.kt             # Background timer service

ui/
├─ components/
│  ├─ AddVitalsDialog.kt
│  ├─ TimerSection.kt
│  └─ VitalListItem.kt
├─ theme/
│  ├─ Color.kt
│  ├─ Dimens.kt
│  ├─ Type.kt
│  └─ VitalsTrackerTheme.kt
├─ MainActivity.kt
└─ VitalsViewModel.kt

utils/
└─ Constants.kt

VitalsApp.kt                  # Application class for Hilt

---

## ⚙ Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/RahulKumar748/Pregnancy-Vitals-Tracker.git
   cd PregnancyVitals
