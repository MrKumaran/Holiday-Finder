package com.holidayfinder.data

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

/* API Response */
//"holiday_id": 14144,
//"name": "New Year's Day",
//"date": "2025-01-01",
//"description": "undefined",
//"occasion_id": 7017,
//"country": "IN",
//"type": "Restricted Holiday",
//"created_at": "2025-01-02 08:28:52",
//"updated_at": null

/* Year limit */
// 2023 to 2040

/* Country */
// https://api.11holidays.com/v1/countries api
// Using country Code 2 letter

@Serializable
data class SavedHoliday(val name:String, val date: String, val type: String, val day: String, val country:String)

data class Holiday(val holiday_id:Int, val name:String, val date:String, val type:String,val country:String)


class DataManager(thisApp: Application): AndroidViewModel(thisApp) {
    var holidayList: List<Holiday> by mutableStateOf(listOf())
    init {
        fetchData("IN","2025")
    }
     fun fetchData(countryCode:String, year:String){
        viewModelScope.launch {
            holidayList = API.holidayService.fetchHoliday(countryCode, year)
        }
    }
}


