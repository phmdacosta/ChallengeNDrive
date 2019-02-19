package com.pedrohenrique.challenge.ndrive.tmdbexplorer.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.Date
import java.util.Calendar
import java.util.Locale

class Movie() : Parcelable {

    var id: Long = 0
    var title: String = ""
    @SerializedName("poster_path")
    var imgPosterPath: String = ""
    @SerializedName("backdrop_path")
    var imgBackdropPath: String = ""
    var releaseDate: Date? = null
    var voteAverage: Double = 0.0
    var voteCount: Int = 0
    @SerializedName("adult")
    var forAdult: Boolean = false
    var overview: String = ""
    var revenue: Long = 0
    var runtime: Long = 0
    var genres: List<Genre> = ArrayList()
    var homepage: String = ""
    var status: String = ""

    val releaseYear: Int
        get() {
            if (releaseDate != null) {
                val calendar = Calendar.getInstance()
                calendar.time = releaseDate
                return calendar.get(Calendar.YEAR)
            }
            return 0
        }

    val runtimeFormated: String
        get() {
            val hour = runtime / 60 as Int
            val minute = runtime % 60 as Int
            return "${hour}h ${minute}m"
        }

    val revenueFormated: String
        get() {
            val format = NumberFormat.getCurrencyInstance(Locale.US)
            return format.format(revenue)
        }

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        imgPosterPath = parcel.readString()
        imgBackdropPath = parcel.readString()
        releaseDate = parcel.readSerializable() as Date
        voteAverage = parcel.readDouble()
        voteCount = parcel.readInt()
        forAdult = parcel.readByte() != 0.toByte()
        overview = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(imgPosterPath)
        parcel.writeString(imgBackdropPath)
        parcel.writeSerializable(releaseDate)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (forAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}