package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.TMDbService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_detail.*

class MovieDetailActivity : AppCompatActivity() {


    private var disposables: CompositeDisposable = CompositeDisposable()
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        movie = intent.getParcelableExtra(Movie.KEY)

        loadDetails()
    }

    private fun loadDetails() {
        val service = Service(this).create(TMDbService::class.java)
        disposables.add(service.getMovieDetail(movie?.id ?: 0, getString(R.string.tmdb_api_key))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<Movie>() {
                override fun onComplete() {
                    movieDetailProgressBar.visibility = View.GONE
                    errorDetail.visibility = View.GONE
                }

                override fun onNext(movie: Movie) {
                    movieTitle.text = movie.title

                    Picasso.get().load(getString(R.string.tmdb_api_url_images).plus(movie.bigImgBackdropPath))
                        .into(movieImgBackdrop)

                    movieOverview.text = movie.overview
                    movieRevunue.text = movie.revenueFormated
                    movieRuntime.text = movie.runtimeFormated

                    detailContainer.visibility = View.VISIBLE
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    movieDetailProgressBar.visibility = View.GONE
                    errorDetail.visibility = View.VISIBLE
                }

            })
        )
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}