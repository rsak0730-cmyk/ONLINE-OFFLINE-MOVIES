# How to get the SyncBeat APK (no Android Studio)

The APK is built automatically by **GitHub Actions**.

## Download steps

1. Open the repo: https://github.com/rsak0730-cmyk/ONLINE-OFFLINE-MOVIES
2. Click the **Actions** tab
3. Open the latest **Build SyncBeat APK** workflow run (green check ✅)
4. Scroll to **Artifacts**
5. Download **SyncBeat-APK**
6. Unzip → install `SyncBeat-v2.1.0-debug.apk` or `SyncBeat-v2.1.0-release.apk` on your phone

## Manual re-build

1. Actions → **Build SyncBeat APK**
2. Click **Run workflow**
3. Choose `both` / `debug` / `release`
4. Wait ~5–10 minutes
5. Download the artifact

## Install on Android

1. Transfer the `.apk` to your phone
2. Settings → allow **Install unknown apps** for Files / Chrome
3. Open the APK and install

## Features in the APK

- Online hub / movies / rooms (WebView)
- Offline library + ExoPlayer
- 14 themes (Neumorphism main)
- Theme changer on Profile

Package: `com.syncbeat.app`
