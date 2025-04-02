package com.holidayfinder.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HolidayAPI {
    @GET("holidays")
    suspend fun fetchHoliday(
        @Query("country") country: String,
        @Query("year") year: String
    ): List<Holiday>
}

object API{
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.11holidays.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val holidayService: HolidayAPI by lazy {
        retrofit.create(HolidayAPI::class.java)
    }
}