package com.holidayfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.holidayfinder.ui.theme.HolidayFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataManager = ViewModelProvider(this)
            .get(DataManager::class.java)
        enableEdgeToEdge()
        setContent {
            HolidayFinderTheme {
                AppStructure(dataManager)
            }
        }
    }
}
