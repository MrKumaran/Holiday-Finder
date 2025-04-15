package com.holidayfinder.nonComposables

import android.content.Context
import com.holidayfinder.data.Holiday
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.collections.toMutableList


// can calculate type, day, month in single function but for simplicity sake let those be separate functions

// to create holiday type to count hash map
fun holidayTypeMap(holidayList:List<Holiday>): HashMap<String,Int> {
    val holidayType: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in holidayList) {
        if (holidayType.containsKey(holiday.type)) {
            holidayType[holiday.type] = holidayType[holiday.type]!! + 1
        } else {
            holidayType[holiday.type] = 1
        }
    }
    return holidayType
}

// to create holiday Day to count hash map
fun holidayDaysMap(holidayList:List<Holiday>): HashMap<String,Int> {
    val holidayDay: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in holidayList) {
        if (holidayDay.containsKey(getDay(holiday.date))) {
            holidayDay[getDay(holiday.date)] = holidayDay[getDay(holiday.date)]!! + 1
        } else {
            holidayDay[getDay(holiday.date)] = 1
        }
    }
    return holidayDay
}

// to create holiday Month to count hash map
fun holidayMonthsMap(holidayList:List<Holiday>): HashMap<String,Int> {
    val holidayMonths: HashMap<String,Int> = mutableMapOf<String,Int>() as HashMap<String,Int>
    for (holiday in holidayList) {
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

// Saving Holiday to internal storage
@OptIn(ExperimentalSerializationApi::class)
fun addingStarHolidayToFile(holiday: Holiday, context: Context): List<Holiday> {
    val filename = "SavedHoliday.json"
    val file = File(context.filesDir, filename)
    val holidayList = if (file.exists()) {
        staredHoliday(context).toMutableList()
    } else {
        mutableListOf()
    }
    holidayList.add(holiday)
    context.openFileOutput(filename, Context.MODE_PRIVATE).use { output ->
        Json.encodeToStream(holidayList, output)
    }
    return holidayList
}

// Reading Holidays from internal storage
@OptIn(ExperimentalSerializationApi::class)
fun staredHoliday(context: Context): List<Holiday> {
    val filename = "SavedHoliday.json"
    val file = File(context.filesDir, filename)
    if (!file.exists()) return emptyList()
    val fileInputStream = context.openFileInput(filename)
    val obj = Json.decodeFromStream<List<Holiday>>(fileInputStream)
    fileInputStream.close()
    return obj
}

// Removing Holiday from internal storage
@OptIn(ExperimentalSerializationApi::class)
fun removingStaredHoliday(context: Context, holiday: Holiday): List<Holiday> {
    val filename = "SavedHoliday.json"
    val fileContent = (staredHoliday(context).toMutableList())
    fileContent.remove(holiday)
    val fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
    Json.encodeToStream(fileContent,fileOutputStream)
    fileOutputStream.close()
    return fileContent
}
