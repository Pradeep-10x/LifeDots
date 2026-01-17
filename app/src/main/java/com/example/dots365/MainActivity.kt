package com.example.dots365

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var showDatePicker by remember { mutableStateOf(false) }

            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Dots365",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Goals • Progress • Time",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(36.dp))

                    // YEARLY
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            UserPrefs.saveYearly(this@MainActivity)
                            openWallpaperPicker()
                        }
                    ) {
                        Text("Yearly Goal (365 Days)")
                    }

                    Spacer(Modifier.height(12.dp))

                    // WEEKLY
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            UserPrefs.saveWeekly(this@MainActivity)
                            openWallpaperPicker()
                        }
                    ) {
                        Text("Weekly Goal (7 Days)")
                    }

                    Spacer(Modifier.height(12.dp))

                    // CUSTOM RANGE
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            showDatePicker = true
                        }
                    ) {
                        Text("Custom Date Range")
                    }
                }
            }

            if (showDatePicker) {
                DateRangePickerScreen(
                    onConfirm = { start, end ->
                        UserPrefs.saveCustomRange(this@MainActivity, start, end)
                        showDatePicker = false
                        openWallpaperPicker()
                    },
                    onDismiss = {
                        showDatePicker = false
                    }
                )
            }
        }
    }

    private fun openWallpaperPicker() {
        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
            putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(
                    this@MainActivity,
                    DotLiveWallpaperService::class.java
                )
            )
        }
        startActivity(intent)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerScreen(
    onConfirm: (Long, Long) -> Unit,
    onDismiss: () -> Unit
) {
    val startPicker = rememberDatePickerState()
    val endPicker = rememberDatePickerState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Date Range") },
                navigationIcon = {
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                actions = {
                    TextButton(onClick = {
                        val start = startPicker.selectedDateMillis ?: return@TextButton
                        val end = endPicker.selectedDateMillis ?: return@TextButton
                        if (end >= start) {
                            onConfirm(start, end)
                        }
                    }) {
                        Text("Apply")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Start Date",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            DatePicker(state = startPicker)

            Spacer(Modifier.height(24.dp))

            Text(
                text = "End Date",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            DatePicker(state = endPicker)

            Spacer(Modifier.height(24.dp))
        }
    }
}
