package com.deviceactivitytracker.data.repository

import com.deviceactivitytracker.data.model.DeviceStatus
import com.deviceactivitytracker.data.network.TrackerApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrackerRepository(private val apiClient: TrackerApiClient) {
    private var isTracking = false

    fun startTracking(phoneNumber: String): Flow<DeviceStatus> = flow {
        isTracking = true
        val rttMeasurements = mutableListOf<Long>()
        val maxMeasurements = 100

        while (isTracking) {
            try {
                val result = apiClient.trackDevice(phoneNumber)
                
                if (result != null) {
                    rttMeasurements.add(result.rttMs)
                    if (rttMeasurements.size > maxMeasurements) {
                        rttMeasurements.removeAt(0)
                    }

                    val avgRtt = rttMeasurements.average()
                    val sortedRtt = rttMeasurements.sorted()
                    val medianRtt = if (sortedRtt.size % 2 == 0) {
                        (sortedRtt[sortedRtt.size / 2 - 1] + sortedRtt[sortedRtt.size / 2]) / 2.0
                    } else {
                        sortedRtt[sortedRtt.size / 2].toDouble()
                    }

                    val threshold = (medianRtt * 0.9).toLong()
                    val status = when {
                        result.rttMs < threshold -> "online"
                        result.rttMs >= threshold -> "standby"
                        else -> "offline"
                    }

                    val deviceStatus = DeviceStatus(
                        phoneNumber = phoneNumber,
                        status = status,
                        rttMs = result.rttMs,
                        avgRttMs = avgRtt,
                        medianRttMs = medianRtt,
                        thresholdMs = threshold
                    )

                    emit(deviceStatus)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Wait before next measurement
            kotlinx.coroutines.delay(2000)
        }
    }

    fun stopTracking() {
        isTracking = false
    }

    suspend fun getTrackingHistory(phoneNumber: String): List<DeviceStatus> {
        return try {
            apiClient.getTrackingHistory(phoneNumber)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
