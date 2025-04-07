package com.holidayfinder

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.holidayfinder.data.DataManager
import com.holidayfinder.pages.holidayPage.HolidayPage

// app layout
@Composable
fun AppStructure(dataManager: DataManager) {
    Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            topBar = {
                TitleBar(
                    location = "India",
                    year = "2025",
                    dataManager = dataManager
                )
            }
        ){ innerPadding ->
            HolidayPage(
                dataManager = dataManager,
                modifier =  Modifier
                    .padding(innerPadding)
            )
        }
    }