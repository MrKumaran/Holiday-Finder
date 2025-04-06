package com.holidayfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.holidayfinder.data.DataManager
import com.holidayfinder.ui.theme.HolidayFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataManager instance
        val dataManager = ViewModelProvider(this)[DataManager::class.java]

        // full screen includes status bar and navigation bar
        enableEdgeToEdge()
        setContent {
            HolidayFinderTheme {
                AppStructure(dataManager)
            }
        }
    }
}
