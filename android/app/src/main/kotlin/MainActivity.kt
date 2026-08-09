package com.deviceactivitytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.deviceactivitytracker.ui.screens.HomeScreen
import com.deviceactivitytracker.ui.theme.DeviceActivityTrackerTheme
import org.koin.android.ext.android.inject
import com.deviceactivitytracker.data.repository.TrackerRepository

class MainActivity : ComponentActivity() {
    private val trackerRepository: TrackerRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceActivityTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(trackerRepository)
                }
            }
        }
    }
}
