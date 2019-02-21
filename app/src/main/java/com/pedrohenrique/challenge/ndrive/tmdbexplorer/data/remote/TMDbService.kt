package com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote

import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbService {
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("discover/movie")
    fun listMovies(@Query("api_key") apiKey: String): Observable<MoviesList>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Long, @Query("api_key") apiKey: String): Observable<Movie>

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") query: String): Observable<MoviesList>
}