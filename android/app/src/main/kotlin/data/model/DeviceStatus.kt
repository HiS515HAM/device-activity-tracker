package com.deviceactivitytracker.data.model

import java.time.LocalDateTime

data class DeviceStatus(
    val phoneNumber: String,
    val status: String, // "online", "standby", "offline"
    val rttMs: Long,
    val avgRttMs: Double,
    val medianRttMs: Double,
    val thresholdMs: Long,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

data class TrackingData(
    val id: String,
    val phoneNumber: String,
    val rttMeasurements: List<Long> = emptyList(),
    val statusHistory: List<DeviceStatus> = emptyList(),
    val startTime: LocalDateTime = LocalDateTime.now(),
    val isActive: Boolean = false
)
