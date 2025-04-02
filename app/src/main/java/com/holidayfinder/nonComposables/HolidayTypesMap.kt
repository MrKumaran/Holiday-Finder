package com.holidayfinder.nonComposables

import com.holidayfinder.DataManager

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