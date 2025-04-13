package com.holidayfinder

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.data.DataManager
import com.holidayfinder.data.countryCodes

// title bar layout
@SuppressLint("UnrememberedMutableState")
@Composable
fun TitleBar(location: String, year: String, dataManager: DataManager) {
    // remembering variables
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(location) }
    var selectedCountryCode by remember { mutableStateOf(countryCodes[location]) }
    var selectedYear by remember { mutableStateOf(year) }

    // Fetching data when country or year changes
    dataManager.fetchData(
        derivedStateOf { selectedCountryCode }.value.toString(),
        derivedStateOf { selectedYear }.value
    )

    Column {
        // Title bar row
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    start = 16.dp,
                    top = 28.dp,
                    end = 24.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Country Row --------------------------------------------------------------------------------------------------------------------------
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { expandedCountry = !expandedCountry }
                ) {
                    Icon(
                        Icons.Outlined.Place,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedCountry,
                    onDismissRequest = { expandedCountry = false },
                    modifier = Modifier
                        .height(600.dp)
                        .width(200.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(4)
                        )
                        .border(.1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    countryCodes.forEach { (country, code) ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = country,
                                    // Font size based on selection
                                    fontSize =
                                        if (country == selectedCountry)
                                            24.sp
                                        else
                                            16.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    // changing font weight based on selection
                                    fontWeight =
                                        if (country == selectedCountry)
                                            FontWeight.ExtraBold
                                        else
                                            FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            onClick = {
                                selectedCountry = country
                                selectedCountryCode = code
                                expandedCountry = false
                            },
                            colors = MenuDefaults.itemColors(MaterialTheme.colorScheme.primary)
                        )
                        HorizontalDivider(
                            thickness = .2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Text(
                    text = selectedCountry,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )

            }
            // Year Row --------------------------------------------------------------------------------------------------------------------------
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { expandedYear = !expandedYear }
                ) {
                    Icon(
                        Icons.Outlined.DateRange,
                        contentDescription = "Year",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedYear,
                    onDismissRequest = { expandedYear = false },
                    modifier = Modifier
                        .height(500.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(4)
                        )
                        .border(.1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    for (years in 2020..2040) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = years.toString(),
                                    // Font size based on selection
                                    fontSize =
                                        if (years.toString() == selectedYear)
                                            24.sp
                                        else
                                            16.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    // Font weight based on selection
                                    fontWeight =
                                        if (years.toString() == selectedYear)
                                            FontWeight.ExtraBold
                                        else
                                            FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            onClick = {
                                selectedYear = years.toString()
                                expandedYear = false
                            },
                            contentPadding = PaddingValues(20.dp, 0.dp, 0.dp, 0.dp)
                        )
                        HorizontalDivider(
                            thickness = .2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Text(
                    text = selectedYear,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}