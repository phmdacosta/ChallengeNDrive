package com.pedrohenrique.challenge.ndrive.tmdbexplorer.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.databinding.MovieDetailBinding
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels.MovieViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieDetailActivity : AppCompatActivity() {


    private var disposables: CompositeDisposable = CompositeDisposable()
    private var movie: Movie? = null
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: MovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.movie_detail
        )

        movie = intent.getParcelableExtra(Movie.KEY)

        loadDetails()
    }

    private fun loadDetails() {
        val service = Service().create(TMDbService::class.java)
        val repository = TMDbRepository(service)
        viewModel = MovieViewModel(repository)
        viewModel.getMovieDetail(movie!!.id)
        binding.movieViewModel = viewModel
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}