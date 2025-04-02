package com.holidayfinder.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.holidayfinder.DataManager
import com.holidayfinder.nonComposables.holidayTypeMap


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
                eventDate = holiday.date,
                eventDay = holiday.description
            )
        }
    }
}

@Composable
private fun HolidayBar(dataManager: DataManager) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        val holidayType = holidayTypeMap(dataManager)
        for (i in holidayType) {
            HolidayType(holidayType = i.key, count = i.value)
        }
    }
}

@Composable
private fun HolidayType(holidayType:String, count:Int) {
         Card(
             modifier = Modifier
                 .padding(
                     horizontal = 20.dp,
                     vertical = 8.dp
                 ),
             colors = cardColors(Color.White),
             border = BorderStroke(1.dp, Color.Black),
             shape = RoundedCornerShape(40),
             elevation = cardElevation(
                 defaultElevation = 18.dp,
                 focusedElevation = 12.dp,
                 pressedElevation = 16.dp,
                 disabledElevation = 0.dp
             )
         ) {
             Row(
                 modifier = Modifier
                     .padding(8.dp)
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
    val textModifier: Modifier = Modifier
        .background(
            color = Color.White,
            shape = RoundedCornerShape(40)
        )
        .padding(horizontal = 12.dp, vertical = 8.dp)
    Card(
        modifier = Modifier
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            )
            .width(360.dp),
        colors = cardColors(Color.Cyan),
        shape = RoundedCornerShape(20),
        elevation = cardElevation(
            defaultElevation = 18.dp,
            focusedElevation = 12.dp,
            pressedElevation = 16.dp,
            disabledElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = eventName,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                modifier = textModifier
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = .5.dp)
            )
            Text(
                text = eventType,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(40)
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Date: $eventDate",
                    fontFamily = FontFamily.SansSerif,
                    modifier = textModifier
                )
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
                Text(
                    text = "Day: $eventDay",
                    fontFamily = FontFamily.SansSerif,
                    modifier = textModifier
                )
            }
        }
    }
}
