/*package com.holidayfinder.pages.holidayPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.holidayfinder.data.DataManager
import com.holidayfinder.nonComposables.holidayTypeMap

// these functions not currently in use but having for future reference
// these will just show the holiday type and count of each type nothing fancy

@Composable
private fun HolidayTypeBar(dataManager: DataManager) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        HolidayType(
            holidayType = "Total",
            count = dataManager.holidayList.size
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        val holidayType = holidayTypeMap(dataManager)
        for (i in holidayType) {
            HolidayType(
                holidayType = i.key,
                count = i.value
            )
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
        border = BorderStroke(.4.dp, Color.Gray),
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
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
            )
            Text(
                text = count.toString(),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(end = 12.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}
*/