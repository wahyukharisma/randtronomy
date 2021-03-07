package com.example.randtronomy.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatLocale {
    private val locale = Locale("en", "UK")

    private val dateDayWithMonth = SimpleDateFormat("EEEE, dd MMMM yyyy", locale)
    private val dateOnly = SimpleDateFormat("yyyy-MM-dd", locale)

    fun dateWithDay(time: String) : String {
        val dateParse = dateOnly.parse(time)
        return dateDayWithMonth.format(dateParse)
    }
}