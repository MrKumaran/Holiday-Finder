package com.holidayfinder.pages.holidayPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.R
import com.holidayfinder.data.DataManager
import com.holidayfinder.filters.Filters
import com.holidayfinder.nonComposables.formatDate
import com.holidayfinder.nonComposables.getDay
import com.holidayfinder.nonComposables.getMonth

// Entire Holiday page layout
@Composable
fun HolidayPage(
    dataManager: DataManager,
    modifier: Modifier = Modifier
) {

    // remember filters variables
    val selectedTypeFilters = remember { mutableStateOf("None") }
    val selectedDayFilters = remember { mutableStateOf("None") }
    val selectedMonthFilters = remember { mutableStateOf("None") }

    LazyColumn(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (dataManager.holidayList.isEmpty()) {
                Image(
                    painter = painterResource(R.drawable.wowsuchempty),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Empty",
                    modifier = Modifier
                        .fillMaxHeight(),
                    alignment = Alignment.Center
                )
            }
        }
        // Filters ------------------------------------------------------------------------------------------
        item {
            Filters.HolidayTypeFilter(
                dataManager = dataManager,
                selectedHolidayType = selectedTypeFilters.value,
                onChange = {
                    selectedTypeFilters.value = it
                })
            Filters.HolidayDayFilter(
                dataManager = dataManager,
                selectedDay = selectedDayFilters.value,
                onChange = {
                    selectedDayFilters.value = it
                })
            Filters.HolidayMonthFilter(
                dataManager = dataManager,
                selectedMonth = selectedMonthFilters.value,
                onChange = {
                    selectedMonthFilters.value = it
                })
        }
        // Cards ---------------------------------
        items(dataManager.holidayList) { holiday ->
            val day = getDay(holiday.date)
            val month = getMonth(holiday.date)
            if (
                (holiday.type == selectedTypeFilters.value || selectedTypeFilters.value == "None") &&
                (day == selectedDayFilters.value || selectedDayFilters.value == "None") &&
                (month == selectedMonthFilters.value || selectedMonthFilters.value == "None")
            ) {
                HolidayCards(
                    eventName = holiday.name,
                    eventType = holiday.type,
                    eventDate = formatDate(holiday.date),
                    eventDay = day
                )
            }
        }
    }
}


// Holiday each card layout
@Composable
private fun HolidayCards(
    eventName: String,
    eventType: String,
    eventDate: String,
    eventDay: String
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .width(400.dp),
        colors = cardColors(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(20),
        elevation = cardElevation(
            defaultElevation = 0.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.sweepGradient(
                colors = if(isSystemInDarkTheme())
                    listOf(Color.Black,Color.Blue, Color.Yellow, Color.Magenta, Color.Black)
                else
                    listOf(Color.White,Color.Black, Color.Blue,Color.Magenta,Color.White),
                center = Offset(200f, 100f)
    )
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            // Event name ------------------------------------------------------------------------------------
            Row {
                Text(
                    text = eventName,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
//                Icon(
//                    imageVector = Icons.Outlined.Star,
//                    contentDescription = "Favorite"
//                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .width(380.dp)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .border(
                        width = .2.dp,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.tertiary
                            ),
                            center = Offset(200f, 100f)
                        ),
                        shape = RoundedCornerShape(20)
                    ),
                thickness = .4.dp
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
            )
            // Event Type ------------------------------------------------------------------------------------
            Text(
                text = eventType,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 2.dp),
                color = MaterialTheme.colorScheme.primary
            )
            // Date and day ------------------------------------------------------------------------------------
            Row(
                modifier = Modifier
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Date ------------------------------------------------------------------------------------
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date:",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                    )
                    Text(
                        text = eventDate,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 12.dp, end = 4.dp, bottom = 12.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                // Day ------------------------------------------------------------------------------------
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Day:",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 4.dp, top = 12.dp, bottom = 12.dp),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )

                    Text(
                        text = eventDay,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 12.dp, end = 8.dp, bottom = 12.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
