package com.holidayfinder.filters

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.data.DataManager
import com.holidayfinder.nonComposables.holidayDaysMap
import com.holidayfinder.nonComposables.holidayMonthsMap
import com.holidayfinder.nonComposables.holidayTypeMap

object Filters {

    // Filter row layout
    @Composable
    fun HolidayDayFilter(dataManager: DataManager,
                         selectedDay: String,
                         onChange: (String) -> Unit
    ) {
        val day = holidayDaysMap(dataManager)
        val filterDayOptions = day.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            for (filterOption in filterDayOptions) {
                FilterChipItem(
                    chipText = filterOption,
                    isSelected = filterOption == selectedDay,
                    count = day[filterOption],
                    onChipClick = { option ->
                        if (option == selectedDay) {
                            onChange("None")
                        } else {
                            onChange(option)
                        }
                    }
                )
            }
        }
    }

    // Filter row layout
    @Composable
    fun HolidayMonthFilter(dataManager: DataManager,
                           selectedMonth: String,
                           onChange: (String) -> Unit
    ) {
        val month = holidayMonthsMap(dataManager)
        val filterMonthOptions = month.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            for (filterOption in filterMonthOptions) {
                FilterChipItem(
                    chipText = filterOption,
                    isSelected = filterOption == selectedMonth,
                    count = month[filterOption],
                    onChipClick = { option ->
                        if (option == selectedMonth) {
                            onChange("None")
                        } else {
                            onChange(option)
                        }
                    }
                )
            }
        }
    }


    // Filter row layout
    @Composable
    fun HolidayTypeFilter(dataManager: DataManager,
                          selectedHolidayType: String,
                          onChange: (String) -> Unit
    ) {

        val holidayType = holidayTypeMap(dataManager)
        val filterOptions = holidayType.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            for (filterOption in filterOptions) {
                FilterChipItem(
                    chipText = filterOption,
                    isSelected = filterOption == selectedHolidayType,
                    count = holidayType[filterOption],
                    onChipClick = { option ->
                        if (option == selectedHolidayType) {
                            onChange("None")
                        } else {
                            onChange(option)
                        }
                    }
                )
            }
        }
    }

    // Filter Chip layout
    @Composable
    private fun FilterChipItem(chipText: String, count: Int?, isSelected: Boolean, onChipClick: (String) -> Unit) {
        ElevatedFilterChip(
            selected = isSelected,
            onClick = { onChipClick(chipText) },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$chipText:",
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                    )
                    Text(
                        text = count.toString(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp
                    )
                }
            },
            leadingIcon = if (isSelected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Selected icon"
                    )
                }
            } else {
                null
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            shape = RoundedCornerShape(50),
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                labelColor = MaterialTheme.colorScheme.primary
            ),
            elevation =  FilterChipDefaults.elevatedFilterChipElevation(
                elevation = 4.dp
            )
        )
    }
}