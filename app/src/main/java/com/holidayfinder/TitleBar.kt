package com.holidayfinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.data.countryCodes

@Composable
fun TitleBar(location:String = "India", year:String = "2025") {

    var expandedCountry by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(location) }
    var selectedCountryCode by remember { mutableStateOf( countryCodes[location]) }
    var selectedYear by remember { mutableStateOf(year) }

    Row(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 16.dp, top = 48.dp, end = 24.dp, bottom = 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = { expandedCountry = !expandedCountry }
        ) {
            Icon(Icons.Outlined.Place, contentDescription = "Location")
        }
        DropdownMenu(
            expanded = expandedCountry,
            onDismissRequest = { expandedCountry = false }
        ) {
            countryCodes.forEach { (country, code) ->
                DropdownMenuItem(
                    text = { Text(text = country) },
                    onClick = {
                        selectedCountry = country
                        selectedCountryCode = code
                        expandedCountry = false
                    },
                    colors = if (country == selectedCountry) MenuDefaults.itemColors(Color.White) else MenuDefaults.itemColors()
                )
                HorizontalDivider(
                    thickness = .1.dp
                )
            }
        }
        Text(
            text = selectedCountry,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp
        )

    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = { expandedYear = !expandedYear }
        ) {
            Icon(Icons.Outlined.DateRange, contentDescription = "Year")
        }
        DropdownMenu(
            expanded = expandedYear,
            onDismissRequest = { expandedYear = false }
        ) {
            for (years in 2020..2040) {
                DropdownMenuItem(
                    text = { Text(text = years.toString()) },
                    onClick = {
                        selectedYear = years.toString()
                        expandedYear = false
                    }
                )
                HorizontalDivider(
                    thickness = .1.dp
                )
            }
        }

        Text(
            text = selectedYear,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp,
        )

    }
}
}