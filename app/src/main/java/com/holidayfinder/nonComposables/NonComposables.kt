package com.holidayfinder.nonComposables

import com.holidayfinder.data.DataManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

// to create holiday type to count hash map
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

// to find the day for given date(string)
fun getDay(dateStr:String):String{
        val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}

// format date to common format (dd-mm-yyy)
fun formatDate(dateStr:String):String{
    val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}