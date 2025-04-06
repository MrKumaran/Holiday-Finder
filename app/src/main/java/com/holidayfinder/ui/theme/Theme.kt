package com.holidayfinder.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = White,
    secondary = Cyan,
    tertiary = Black,
    background = Transparent,
    surface = Black,
    onPrimary = Filter_70White,
    onSecondary = Filer_70Cyan,
    onTertiary = Filter_70Black,
    onBackground = Transparent,
    onSurface = Filter_50Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    secondary = Cyan,
    tertiary = White,
    background = Transparent,
    surface = White,
    onPrimary = Filter_70Black,
    onSecondary = Filer_70Cyan,
    onTertiary = Filter_70White,
    onBackground = Transparent,
    onSurface = Filter_50White,
)

@Composable
fun HolidayFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    //val colorScheme = if (darkTheme) {DarkColorScheme} else{LightColorScheme}

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}