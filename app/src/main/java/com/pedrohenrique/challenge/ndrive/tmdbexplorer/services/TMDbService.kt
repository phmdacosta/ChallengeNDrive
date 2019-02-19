package com.pedrohenrique.challenge.ndrive.tmdbexplorer.services

import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TMDbService {
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("discover/movie")
    fun listMovies(@Query("api_key") apiKey: String): Call<MoviesList>
//    fun listMovies(@Query("api_key") apiKey: String): Observable<MoviesList>
}