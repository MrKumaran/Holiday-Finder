package com.holidayfinder

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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

data class Holiday(val holiday_id:String, val name:String, val date:String,
                   val description:String, val occasion_id:String, val country:String,
                   val type:String, val created_at:String, val updated_at:String)

class DataManager(ThisApp: Application): AndroidViewModel(ThisApp) {
    var holidayList: List<Holiday> by mutableStateOf(listOf())
    init {
        fetchData()
    }
     fun fetchData(){
        viewModelScope.launch {
        holidayList = API.holidayService.fetchHoliday("IN", "2025")
        }
    }
}