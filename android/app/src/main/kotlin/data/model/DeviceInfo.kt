package com.deviceactivitytracker.data.model

import android.os.Build
import java.time.LocalDateTime

data class DeviceInfo(
    val phoneNumber: String,
    val deviceName: String,
    val manufacturer: String,
    val model: String,
    val androidVersion: Int,
    val osVersion: String,
    val cpuAbi: String,
    val ramMemory: Long,
    val storageSpace: Long,
    val screenDensity: Float,
    val screenResolution: String,
    val batteryHealth: String = "Unknown",
    val isCharging: Boolean = false,
    val batteryPercentage: Int = 0,
    val isRooted: Boolean = false,
    val recordedAt: LocalDateTime = LocalDateTime.now()
)\n\ndata class DeviceStatus(\n    val phoneNumber: String,\n    val deviceInfo: DeviceInfo? = null,\n    val status: String, // \"online\", \"standby\", \"offline\"\n    val rttMs: Long,\n    val avgRttMs: Double,\n    val medianRttMs: Double,\n    val thresholdMs: Long,\n    val timestamp: LocalDateTime = LocalDateTime.now()\n)\n\ndata class TrackingData(\n    val id: String,\n    val phoneNumber: String,\n    val deviceInfo: DeviceInfo? = null,\n    val rttMeasurements: List<Long> = emptyList(),\n    val statusHistory: List<DeviceStatus> = emptyList(),\n    val startTime: LocalDateTime = LocalDateTime.now(),\n    val isActive: Boolean = false\n)\n\nobject DeviceInfoCollector {\n    fun getDeviceInfo(phoneNumber: String): DeviceInfo {\n        val runtime = Runtime.getRuntime()\n        val totalMemory = runtime.totalMemory() / (1024 * 1024) // MB\n        val maxMemory = runtime.maxMemory() / (1024 * 1024) // MB\n        \n        return DeviceInfo(\n            phoneNumber = phoneNumber,\n            deviceName = Build.DEVICE,\n            manufacturer = Build.MANUFACTURER,\n            model = Build.MODEL,\n            androidVersion = Build.VERSION.SDK_INT,\n            osVersion = Build.VERSION.RELEASE,\n            cpuAbi = Build.CPU_ABI,\n            ramMemory = maxMemory,\n            storageSpace = 0L, // Will be set later\n            screenDensity = 0f, // Will be set from context\n            screenResolution = \"${Build.FINGERPRINT}\"\n        )\n    }\n}\n