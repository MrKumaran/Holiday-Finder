package com.holidayfinder.nonComposables

import com.holidayfinder.data.DataManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

// can calculate type, day, month in single function but for simplicity sake let those be separate functions

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

// to create holiday Day to count hash map
fun holidayDaysMap(dataManager: DataManager): HashMap<String,Int> {
    val holidayDay: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in dataManager.holidayList) {
        if (holidayDay.containsKey(getDay(holiday.date))) {
            holidayDay[getDay(holiday.date)] = holidayDay[getDay(holiday.date)]!! + 1
        } else {
            holidayDay[getDay(holiday.date)] = 1
        }
    }
    return holidayDay
}

// to create holiday Month to count hash map
fun holidayMonthsMap(dataManager: DataManager): HashMap<String,Int> {
    val holidayMonths: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in dataManager.holidayList) {
        if (holidayMonths.containsKey(getMonth(holiday.date))) {
            holidayMonths[getMonth(holiday.date)] = holidayMonths[getMonth(holiday.date)]!! + 1
        } else {
            holidayMonths[getMonth(holiday.date)] = 1
        }
    }
    return holidayMonths
}

// to find the month for given date(string)
fun getMonth(dateStr:String):String{
    val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
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
