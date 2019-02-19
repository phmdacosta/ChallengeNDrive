package com.pedrohenrique.challenge.ndrive.tmdbexplorer.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        val date = json?.asString ?: ""

        val format = SimpleDateFormat("yyyy-MM-dd", Locale("US"))

        try {
            return format.parse(date)
        } catch (exp: ParseException) {
            Log.e("DateDeserializer", "Failed to parse Date:".plus(exp))
            return null
        }

    }
}