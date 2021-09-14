package com.chavesdev.zronews.common

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.format(format: String = "dd 'de' MMMM") : String? {
    val dateFormat = SimpleDateFormat(format)
    try {
        return dateFormat.format(this)
    } catch (ex: ParseException){
        Log.e("DateToString", ex.message ?: "")
    }
    return null
}