package com.holidayfinder.filters

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.data.Holiday
import com.holidayfinder.nonComposables.holidayDaysMap
import com.holidayfinder.nonComposables.holidayMonthsMap
import com.holidayfinder.nonComposables.holidayTypeMap
import com.holidayfinder.ui.theme.Black


object Filters {

    // Filter row layout
    @Composable
    fun HolidayDayFilter(
        holidayList:List<Holiday>,
        selectedDay: String,
        onChange: (String) -> Unit
    ) {
        val day = holidayDaysMap(holidayList)
        val filterDayOptions = day.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
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
    fun HolidayMonthFilter(
        holidayList:List<Holiday>,
        selectedMonth: String,
        onChange: (String) -> Unit
    ) {
        val month = holidayMonthsMap(holidayList)
        val filterMonthOptions = month.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
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
    fun HolidayTypeFilter(
        holidayList:List<Holiday>,
        selectedHolidayType: String,
        onChange: (String) -> Unit
    ) {

        val holidayType = holidayTypeMap(holidayList)
        val filterOptions = holidayType.keys.toList()

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 0.dp)
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
    private fun FilterChipItem(
        chipText: String,
        count: Int?,
        isSelected: Boolean,
        onChipClick: (String) -> Unit
    ) {
        FilterChip(
            selected = isSelected,
            onClick = { onChipClick(chipText) },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$chipText:",
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        color =
                            if(isSystemInDarkTheme())
                                if (!isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Black
                            else MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                    )
                    Text(
                        text = count.toString(),
                        color =
                            if(isSystemInDarkTheme())
                                if (!isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Black
                            else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp
                    )
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
            shape = RoundedCornerShape(40),
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                selectedLabelColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.background,
                labelColor = MaterialTheme.colorScheme.background
            ),
            elevation = FilterChipDefaults.elevatedFilterChipElevation(
                elevation = 0.dp
            )
        )
    }
}