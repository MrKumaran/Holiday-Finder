package com.holidayfinder.nonComposables

import com.holidayfinder.DataManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun holidayTypeMap(dataManager: DataManager): HashMap<String,Int> {
    val holidayType: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in dataManager.holidayList) {
        if (holidayType.containsKey(holiday.type)) {
            holidayType[holiday.type] = holidayType[holiday.type]!! + 1
        } else {
            holidayType[holiday.type] = 1
        }
    }
    return holidayType
}

fun getDay(date:String):String{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(date, formatter)
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}