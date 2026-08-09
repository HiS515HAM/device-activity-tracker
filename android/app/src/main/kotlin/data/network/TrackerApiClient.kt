package com.deviceactivitytracker.data.network

import com.deviceactivitytracker.data.model.DeviceStatus
import com.deviceactivitytracker.data.model.DeviceInfo
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class TrackingResult(
    @SerializedName("rttMs")
    val rttMs: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("timestamp")
    val timestamp: String
)

data class DeviceInfoRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("deviceInfo")
    val deviceInfo: DeviceInfo
)

interface TrackerApiService {
    @GET("/api/track")
    suspend fun trackDevice(
        @Query("phone") phoneNumber: String
    ): TrackingResult

    @GET("/api/history")
    suspend fun getHistory(
        @Query("phone") phoneNumber: String
    ): List<TrackingResult>
    
    @POST("/api/device-info")
    suspend fun sendDeviceInfo(
        @Body deviceInfoRequest: DeviceInfoRequest
    ): Boolean
}

class TrackerApiClient(baseUrl: String = "http://10.0.2.2:3001") {
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(TrackerApiService::class.java)

    suspend fun trackDevice(phoneNumber: String): TrackingResult? {
        return try {
            apiService.trackDevice(phoneNumber)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getTrackingHistory(phoneNumber: String): List<DeviceStatus> {
        return try {
            apiService.getHistory(phoneNumber).map { result ->
                DeviceStatus(
                    phoneNumber = phoneNumber,
                    status = result.status,
                    rttMs = result.rttMs,
                    avgRttMs = result.rttMs.toDouble(),
                    medianRttMs = result.rttMs.toDouble(),
                    thresholdMs = (result.rttMs * 0.9).toLong()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun sendDeviceInfo(phoneNumber: String, deviceInfo: DeviceInfo): Boolean {
        return try {
            apiService.sendDeviceInfo(DeviceInfoRequest(phoneNumber, deviceInfo))
        } catch (e: Exception) {
            false
        }
    }
}
