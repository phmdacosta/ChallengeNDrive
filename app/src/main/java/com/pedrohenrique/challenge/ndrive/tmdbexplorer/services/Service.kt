package com.pedrohenrique.challenge.ndrive.tmdbexplorer.services

import android.content.Context
import android.util.JsonReader
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service(context: Context) {

    val baseUrl = context.getString(R.string.tmdb_api_url)

    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

//    private fun createGsonConverter(): Converter.Factory {
//        val r = JsonReader()
//        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(RedirectionInfo::class.java, RedirectionInfoDeserializer())
//        val gson = gsonBuilder.create()
//        return GsonConverterFactory.create(gson)
//    }
}