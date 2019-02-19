package com.pedrohenrique.challenge.ndrive.tmdbexplorer.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class MoviesList() : Parcelable {
    var totalResults: Long = 0
    var totalPages: Long = 0
    var page: Int = 0
    private var results: List<Movie> = ArrayList()

    fun get(index: Int): Movie {
        return results[index]
    }

    fun toList(): List<Movie> {
        return results
    }

    constructor(parcel: Parcel) : this() {
        totalResults = parcel.readLong()
        totalPages = parcel.readLong()
        page = parcel.readInt()
        results = parcel.createTypedArrayList(Movie.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(totalResults)
        parcel.writeLong(totalPages)
        parcel.writeInt(page)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesList> {
        override fun createFromParcel(parcel: Parcel): MoviesList {
            return MoviesList(parcel)
        }

        override fun newArray(size: Int): Array<MoviesList?> {
            return arrayOfNulls(size)
        }
    }
}