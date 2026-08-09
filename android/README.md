# Device Activity Tracker - Android Application

## Overview

This is the Android mobile application for the Device Activity Tracker project. It provides a native Android interface for tracking device activity using WhatsApp and Signal RTT analysis.

## Features

- **Real-time Device Tracking**: Monitor device activity status (Online/Standby/Offline)
- **RTT Measurements**: Display Round-Trip Time measurements
- **Activity History**: View tracking history and statistics
- **Modern UI**: Built with Jetpack Compose for a modern Material Design 3 interface
- **Background Service**: Continuous tracking with background service support
- **Foreground Service**: Always-on tracking with notification

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Repository Pattern
- **Networking**: Retrofit + OkHttp
- **Dependency Injection**: Koin
- **Database**: Room (for tracking history)
- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)

## Requirements

- Android Studio Flamingo or later
- Android SDK 34
- Kotlin 1.9.20+
- Gradle 8.2.0+

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/HiS515HAM/device-activity-tracker.git
cd device-activity-tracker/android
```

### 2. Open in Android Studio

```bash
studio .
```

### 3. Sync Gradle

Android Studio will automatically sync Gradle files. Wait for completion.

### 4. Configure Backend URL

Edit `TrackerApiClient.kt` to set your backend server URL:

```kotlin
class TrackerApiClient(baseUrl: String = "http://10.0.2.2:3001") {
    // ...
}
```

Note: `10.0.2.2` is the special alias for localhost when running on Android emulator.

### 5. Build and Run

```bash
# Build
./gradlew build

# Run on emulator or device
./gradlew installDebug
```

## Project Structure

```
android/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── kotlin/
│   │       │   ├── MainActivity.kt
│   │       │   ├── MyApplication.kt
│   │       │   ├── data/
│   │       │   │   ├── model/
│   │       │   │   │   └── DeviceStatus.kt
│   │       │   │   ├── network/
│   │       │   │   │   └── TrackerApiClient.kt
│   │       │   │   └── repository/
│   │       │   │       └── TrackerRepository.kt
│   │       │   ├── service/
│   │       │   │   └── TrackingService.kt
│   │       │   ├── ui/
│   │       │   │   ├── screens/
│   │       │   │   │   └── HomeScreen.kt
│   │       │   │   └── theme/
│   │       │   │       └── Theme.kt
│   │       │   └── di/
│   │       │       └── KoinModule.kt
│   │       ├── res/
│   │       │   ├── values/
│   │       │   │   ├── strings.xml
│   │       │   │   ├── colors.xml
│   │       │   │   └── themes.xml
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Permissions

The application requires the following permissions:

- `INTERNET` - For network communication
- `ACCESS_NETWORK_STATE` - To check network connectivity
- `CHANGE_NETWORK_STATE` - For network operations
- `READ_PHONE_STATE` - For device state monitoring
- `READ_CALL_LOG` - For call log analysis (optional)

## Usage

1. **Launch the Application**
   - Open the app on your Android device or emulator

2. **Enter Target Phone Number**
   - Enter the phone number of the device you want to track (e.g., +491701234567)

3. **Start Tracking**
   - Tap the "Start" button to begin tracking
   - The app will display real-time status updates

4. **Monitor Activity**
   - View current device status (Online/Standby/Offline)
   - Check RTT measurements and statistics
   - Review activity history

5. **Stop Tracking**
   - Tap the "Stop" button to end tracking

## API Integration

The app communicates with the Node.js backend API:

```
GET /api/track?phone=<phoneNumber>
Response: {
  "rttMs": 1000,
  "status": "online",
  "timestamp": "2024-01-15T10:30:00Z"
}
```

## Building Release APK

```bash
# Build release APK
./gradlew assembleRelease

# APK location: app/build/outputs/apk/release/app-release.apk
```

## Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Troubleshooting

### Backend Connection Issues
- Ensure backend server is running on the configured port
- For emulator: Use `10.0.2.2` instead of `localhost`
- For physical device: Use your computer's actual IP address

### Permissions Not Working
- Ensure app permissions are granted in Settings > Apps > Device Activity Tracker
- For Android 6.0+, runtime permissions are required

### Gradle Sync Failures
- Run `./gradlew clean`
- Invalidate Android Studio caches: File > Invalidate Caches
- Update Gradle plugin to latest version

## Ethical & Legal Considerations

⚠️ **DISCLAIMER**: This application is for research and educational purposes only.

Never track people without explicit consent - this may violate privacy laws and regulations.

## Contributing

Contributions are welcome! Please see the main repository's CONTRIBUTING.md.

## License

MIT License - See LICENSE file in the main repository.

## Support

For issues and questions, please open an issue in the main repository.
