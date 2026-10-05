# SyncBeat Android App

**Online + Offline Movies** — Hybrid native Android app for the SyncBeat platform.

## Features

- 🌐 **Online Hub** – Full SyncBeat PWA (movies, watch parties, rooms) via WebView
- 🎬 **Movies** – Dedicated movies page
- 📁 **Offline Library** – Pick any video from your device & play with ExoPlayer (Media3)
- 👥 **Rooms** – Watch party / multi-device sync
- 🎨 Dark Material 3 UI matching the SyncBeat brand (`#07070A`)

## Tech Stack

- Kotlin
- Jetpack Compose + Material 3
- Navigation Compose
- Media3 ExoPlayer (offline player)
- AndroidX WebView / WebKit
- Min SDK 26 / Target SDK 35

## How to open & build

1. Clone this repository
2. Open the `android/` folder in **Android Studio** (Hedgehog or newer recommended)
3. Let Gradle sync
4. Run on emulator or physical device

```
android/
├── app/
│   ├── src/main/java/com/syncbeat/app/
│   │   ├── MainActivity.kt
│   │   ├── navigation/
│   │   ├── player/          ← ExoPlayer offline player
│   │   └── ui/screens/      ← Home, Movies, Library, Rooms, Profile
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Package

`com.syncbeat.app`

## Notes

- The Online / Movies / Rooms tabs load your existing GitHub Pages PWA:
  `https://rsak0730-cmyk.github.io/ONLINE-OFFLINE-MOVIES/`
- Offline Library works fully without internet.
- Update the URLs in `HomeScreen.kt`, `MoviesScreen.kt`, `RoomsScreen.kt` if you change hosting.

## Asset Links

If you publish this app, update `assetlinks.json` on the website with the new package name and your signing certificate SHA-256.

```
package_name: com.syncbeat.app
```

Made for the SyncBeat / ONLINE-OFFLINE-MOVIES project.
