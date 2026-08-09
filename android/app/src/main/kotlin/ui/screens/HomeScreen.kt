package com.deviceactivitytracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deviceactivitytracker.data.model.DeviceStatus
import com.deviceactivitytracker.data.repository.TrackerRepository

@Composable
fun HomeScreen(trackerRepository: TrackerRepository) {
    var targetPhoneNumber by remember { mutableStateOf("") }
    var isTracking by remember { mutableStateOf(false) }
    var deviceStatus by remember { mutableStateOf<DeviceStatus?>(null) }
    var rttHistory by remember { mutableStateOf<List<Long>>(emptyList()) }

    LaunchedEffect(isTracking) {
        if (isTracking && targetPhoneNumber.isNotEmpty()) {
            trackerRepository.startTracking(targetPhoneNumber).collect { status ->
                deviceStatus = status
                rttHistory = rttHistory + status.rttMs
                if (rttHistory.size > 100) {
                    rttHistory = rttHistory.drop(1)
                }
            }
        } else {
            trackerRepository.stopTracking()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Device Activity Tracker") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Phone Number Input
            OutlinedTextField(
                value = targetPhoneNumber,
                onValueChange = { targetPhoneNumber = it },
                label = { Text("Target Phone Number") },
                placeholder = { Text("e.g., +491701234567") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = !isTracking,
                singleLine = true
            )

            // Control Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { isTracking = true },
                    enabled = targetPhoneNumber.isNotEmpty() && !isTracking,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = "Start")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Start")
                }

                Button(
                    onClick = { isTracking = false },
                    enabled = isTracking,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(Icons.Filled.Stop, contentDescription = "Stop")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Stop")
                }
            }

            // Status Display
            if (deviceStatus != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Device Status",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        val statusColor = when (deviceStatus?.status) {
                            "online" -> Color.Green
                            "standby" -> Color.Yellow
                            "offline" -> Color.Red
                            else -> Color.Gray
                        }

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(
                                    color = statusColor.copy(alpha = 0.2f),
                                    shape = MaterialTheme.shapes.large
                                )
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                deviceStatus?.status?.uppercase() ?: "UNKNOWN",
                                style = MaterialTheme.typography.titleLarge,
                                color = statusColor
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatItem(label = "RTT", value = "${deviceStatus?.rttMs}ms")
                            StatItem(label = "Avg RTT", value = "${deviceStatus?.avgRttMs?.toInt()}ms")
                            StatItem(label = "Median", value = "${deviceStatus?.medianRttMs?.toInt()}ms")
                        }
                    }
                }
            }

            // RTT History Chart (placeholder)
            if (rttHistory.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "RTT History (Last ${rttHistory.size} measurements)",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            "Min: ${rttHistory.minOrNull()}ms | Max: ${rttHistory.maxOrNull()}ms",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
