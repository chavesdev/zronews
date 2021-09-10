package com.chavesdev.zronews.common

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'") : Date? {
    val dateFormat = SimpleDateFormat(format)
    try {
        return dateFormat.parse(this)
    } catch (ex: ParseException){
        Log.e("StringToDate", ex.message ?: "")
    }
    return null
}