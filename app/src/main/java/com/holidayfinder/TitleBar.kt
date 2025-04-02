package com.holidayfinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleBar(location:String = "India", year:String = "2025") {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 16.dp, top = 48.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = Icons.Outlined.Place,
            contentDescription = "Location Icon",
            modifier = Modifier
                .height(28.dp),
            contentScale = ContentScale.FillHeight
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Text(
            text = location,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp
        )

    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "Location Icon",
            modifier = Modifier
                .height(28.dp),
            contentScale = ContentScale.FillHeight
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Text(
            text = year,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp
        )

    }
}
}