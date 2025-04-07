package com.holidayfinder.pages.holidayPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.holidayfinder.ui.theme.Filter_70Black
import com.holidayfinder.ui.theme.White


// Entire Holiday page layout
@Composable
fun HolidayPage(dataManager: DataManager, modifier: Modifier = Modifier) {

    // remember filters variables
    val selectedTypeFilters = remember { mutableStateOf("None") }
    val selectedDayFilters = remember { mutableStateOf("None") }
    val selectedMonthFilters = remember { mutableStateOf("None") }

        LazyColumn(
            modifier = modifier
                .background(White),
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
private fun HolidayCards(eventName: String, eventType: String, eventDate: String, eventDay: String) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .width(400.dp),
        colors = cardColors(Color.Cyan),
        shape = RoundedCornerShape(20),
        elevation = cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            // Event name ------------------------------------------------------------------------------------
            Text(
                text = eventName,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(45),
                        clip = true,
                        spotColor = Color.Black
                    )
                    .background(
                        color = White,
                        shape = RoundedCornerShape(45)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .width(380.dp)
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                thickness = .4.dp,
                color = Filter_70Black
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
                    .padding(horizontal = 12.dp, vertical = 2.dp)
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
                            .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
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
                            .padding(top = 12.dp, end = 4.dp, bottom = 12.dp)
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
                            .padding(start = 4.dp, top = 12.dp, bottom = 12.dp)
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )

                    Text(
                        text = eventDay,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 12.dp, end = 8.dp, bottom = 12.dp)
                    )
                }
            }
        }
    }
}
