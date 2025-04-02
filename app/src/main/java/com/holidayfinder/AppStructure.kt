package com.holidayfinder

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.holidayfinder.pages.HolidayPage

@Composable
fun AppStructure(dataManager:DataManager) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = { TitleBar("India", "2025") }
        ){ innerPadding ->
        HolidayPage(dataManager = dataManager,
            modifier =  Modifier.padding(innerPadding)
        )
    }
}