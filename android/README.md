# SyncBeat Android — v2.0 Neumorphism Edition

Modern hybrid Android app for **ONLINE-OFFLINE-MOVIES / SyncBeat**.

## What's new in v2.0
- **Neumorphism** as the main default theme (soft raised UI, dual shadows)
- **14 real themes** with live theme changer (persisted via DataStore)
- Modern buttons, cards, bottom nav, empty states
- All original features preserved

## Features (unchanged + enhanced)
| Tab | Feature |
|-----|---------|
| **Home** | Online SyncBeat hub (WebView) |
| **Movies** | movies.html online catalog |
| **Offline** | Local file picker + **ExoPlayer** playback |
| **Rooms** | Watch party / multi-device sync hub |
| **Profile** | About + **14-theme switcher** |

## Themes (14)
1. **Neumorphism** (MAIN default)
2. Neumorphism Dark
3. Soft Clay
4. Ocean Breeze
5. Ice Crystal
6. Warm Sand
7. Rose Gold
8. Forest Mist
9. Midnight AMOLED
10. Sunset Glow
11. Purple Haze
12. Cyber Neon
13. Emerald Night
14. High Contrast

## Tech
- Kotlin · Jetpack Compose · Material 3
- Media3 ExoPlayer · WebView · DataStore Preferences
- Package: `com.syncbeat.app`
- Min SDK 26 · Target 35 · Version 2.0.0

## Open & run
1. Open the **`android/`** folder in Android Studio
2. Gradle sync
3. Run on device / emulator
4. Profile → Themes → tap any swatch to switch (saved automatically)

## Project structure
```
android/app/src/main/java/com/syncbeat/app/
├── MainActivity.kt
├── data/ThemeManager.kt
├── navigation/
├── player/PlayerActivity.kt      ← offline ExoPlayer
├── ui/
│   ├── components/Neumorphic.kt  ← NeuButton, NeuCard, …
│   ├── screens/                  ← Home, Movies, Library, Rooms, Profile
│   └── theme/                    ← AppThemes (14), Theme, Type
```
