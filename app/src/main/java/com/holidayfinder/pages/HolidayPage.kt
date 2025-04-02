package com.holidayfinder.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.DataManager
import com.holidayfinder.R
import com.holidayfinder.nonComposables.formatDate
import com.holidayfinder.nonComposables.getDay
import com.holidayfinder.nonComposables.holidayTypeMap
import com.holidayfinder.ui.theme.littleWhite


@Composable
fun HolidayPage(dataManager: DataManager, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            HolidayBar(dataManager)
        }
        items(dataManager.holidayList) { holiday ->
            HolidayCards(
                eventName = holiday.name,
                eventType = holiday.type,
                eventDate = formatDate(holiday.date),
                eventDay = getDay(holiday.date)
            )
        }
    }
}

@Composable
private fun HolidayBar(dataManager: DataManager) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        val holidayType = holidayTypeMap(dataManager)
        for (i in holidayType) {
            HolidayType(holidayType = i.key, count = i.value)
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun HolidayType(holidayType:String, count:Int) {
         Card(
             modifier = Modifier
                 .padding(
                     top = 8.dp,
                     bottom = 8.dp,
                 ),
             colors = cardColors(Color.White),
             border = BorderStroke(.2.dp, Color.Gray),
             shape = RoundedCornerShape(50),
             elevation = cardElevation(
                 defaultElevation = 2.dp,
                 focusedElevation = 0.dp,
                 pressedElevation = 0.dp,
                 disabledElevation = 0.dp
             )
         ) {
             Row(
                 modifier = Modifier
                     .padding(1.dp)
             ) {
                 Text(
                     text = "$holidayType:",
                     modifier = Modifier
                         .padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
                 )
                 Spacer(
                     modifier = Modifier
                         .padding(horizontal = 2.dp)
                 )
                 Text(
                     text = count.toString(),
                     modifier = Modifier
                         .padding(end = 12.dp, top = 8.dp, bottom = 8.dp)
                 )
             }
         }
}

@Composable
private fun HolidayCards(eventName: String, eventType: String, eventDate: String, eventDay: String) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .width(360.dp),
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
            Text(
                text = eventName,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(20),
                        clip = true,
                        spotColor = Color.Black
                    )
                    .background(
                        color = littleWhite,
                        shape = RoundedCornerShape(20)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
            )
            Text(
                text = eventType,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
