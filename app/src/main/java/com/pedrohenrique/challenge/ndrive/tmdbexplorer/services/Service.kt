package com.pedrohenrique.challenge.ndrive.tmdbexplorer.services

import android.content.Context
import android.util.JsonReader
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.utils.DateDeserializer
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class Service(context: Context) {

    val baseUrl = context.getString(R.string.tmdb_api_url)

    var retrofit: Retrofit

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}