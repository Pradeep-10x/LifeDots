###LifeDots — Live Goal-Tracking Wallpaper

LifeDots is a minimal Android live wallpaper that visualizes time, goals, and progress using dots.
Each dot represents a unit of time — helping you see your progress every time you unlock your phone.

No accounts.
No ads.
No noise.
Just time moving forward.

✨ Features

*** Live Wallpaper (works on lock & home screen)

📅 Multiple Goal Modes

Yearly Goal — 365 dots (one per day)

Weekly Goal — 7-day cycle

Custom Date Range — choose any start & end date

🎯 Clear Progress Visualization

Past days → White

Current day → Orange

Future days → Grey

⚡ Lightweight & Offline

📱 Tested on Realme / Narzo / Oppo devices

🧠 How It Works

The wallpaper renders a grid of dots

Dot colors update based on:

current date

selected goal type

The wallpaper redraws automatically when it becomes visible

You don’t need background services, alarms, or battery-heavy tricks.

🖼 Visual Logic
Dot State	Meaning
⚪ White	Completed / past days
🟠 Orange	Today
⚫ Grey	Upcoming days
🏗 Project Structure
app/
 ├── MainActivity.kt              # Goal selection UI
 ├── DotLiveWallpaperService.kt   # Live wallpaper engine
 ├── DotRenderer.kt               # Core drawing logic (canvas)
 ├── DateUtils.kt                 # Date calculations
 ├── UserPrefs.kt                 # Goal persistence
 ├── GoalType.kt                  # Goal modes enum
 ├── res/xml/dot_wallpaper.xml    # Wallpaper metadata
 └── AndroidManifest.xml

🚀 Getting Started
1️⃣ Clone the repo
git clone https://github.com/yourusername/dots365.git

2️⃣ Open in Android Studio

Android Studio Giraffe or newer

Kotlin + Jetpack Compose

3️⃣ Run on device

4️⃣ Set the wallpaper

Open the app

Select a goal (Yearly / Weekly / Custom)

Choose Dots365 from the live wallpaper picker

📦 Build APK
./gradlew assembleRelease


APK will be generated in:

app/build/outputs/apk/release/

🧪 Tested Devices

✅ Realme Narzo 50 Pro

✅ Oppo ColorOS

✅ Android 12+


🔒 Permissions
Permission	Reason
SET_WALLPAPER	Apply live wallpaper
BIND_WALLPAPER	Required by Android system

No internet access.
No background tracking.  # No security/privacy risk .. ;)

🤝 Contributing

Pull requests are welcome.
If you have an idea, open an issue first to discuss it.


🙌 Author

Built with focus and obsession by Pradeep Singh.

If this helped you stay consistent —
⭐ star the repo.
