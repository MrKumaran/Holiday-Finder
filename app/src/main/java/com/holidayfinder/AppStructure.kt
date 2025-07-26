package com.holidayfinder

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.holidayfinder.data.DataManager

// app layout
@Composable
fun AppStructure(dataManager: DataManager) {
    var savedPageToggle by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
            contentWindowInsets = WindowInsets.safeDrawing,
            topBar = {
                TitleBar(
                    location = "India",
                    year = "2025",
                    dataManager = dataManager
                )
            },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {savedPageToggle = !savedPageToggle},
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .border(
                        width = 1.dp, brush = Brush.sweepGradient(
                            colors = if (isSystemInDarkTheme())
                                listOf(Color.Black, Color.Blue, Color.Yellow, Color.Magenta, Color.Black)
                            else
                                listOf(Color.White, Color.Black, Color.Blue, Color.Magenta, Color.White),
                            center = Offset(200f, 100f)),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        ) { innerPadding ->
            HomePage(
                dataManager = dataManager,
                savedPageToggle = savedPageToggle,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }