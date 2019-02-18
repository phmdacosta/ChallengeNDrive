package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.TMDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity() {

    private var moviesList: List<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list)

        val service = Service(this).create(TMDbService::class.java)
        val call: Call<MoviesList> = service.listMovies(getString(R.string.tmdb_api_key))

        call.enqueue(object: Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                val list = response.body()
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                t.printStackTrace()
                // Do nothing
            }
        })
    }
}