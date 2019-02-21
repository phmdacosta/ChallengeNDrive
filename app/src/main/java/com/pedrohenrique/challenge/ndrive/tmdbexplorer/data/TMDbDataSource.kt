package com.pedrohenrique.challenge.ndrive.tmdbexplorer.data

import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbApiConfig
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class TMDbDataSource(val service: TMDbService) : DataSource<Movie> {

    override fun list(success: (List<Movie>) -> Unit, error: () -> Unit) : Disposable {
        return service.listMovies(TMDbApiConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<MoviesList>() {
                override fun onComplete() {
//                    hideProgressBar()
//                    msgEmptyList.visibility = View.GONE
                }

                override fun onNext(t: MoviesList) {
                    success(t.toList())
//                    val adapter = MoviesListAdapter(activity!!, listener, t.toList())
//                    moviesListView.adapter = adapter
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
//                    hideProgressBar()
//                    msgEmptyList.visibility = View.VISIBLE
                }

            })
    }

    override fun getDetail(id: Long, success: (Movie) -> Unit, error: () -> Unit) : Disposable {
        return service.getMovieDetail(id, TMDbApiConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<Movie>() {
                override fun onComplete() {
//                    movieDetailProgressBar.visibility = View.GONE
//                    errorDetail.visibility = View.GONE
                }

                override fun onNext(movie: Movie) {
                    success(movie)
//                    movieTitle.text = movie.title
//
//                    Picasso.get().load(getString(R.string.tmdb_api_url_images).plus(movie.bigImgBackdropPath))
//                        .into(movieImgBackdrop)
//
//                    movieOverview.text = movie.overview
//                    movieRevunue.text = movie.revenueFormated
//                    movieRuntime.text = movie.runtimeFormated
//
//                    detailContainer.visibility = View.VISIBLE
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
//                    movieDetailProgressBar.visibility = View.GONE
//                    errorDetail.visibility = View.VISIBLE
                }

            })
    }

    override fun search(query: String, success: (List<Movie>) -> Unit, error: () -> Unit) : Disposable {
        return service.searchMovie(TMDbApiConfig.API_KEY, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<MoviesList>() {
                override fun onComplete() {
//                    hideProgressBar()
//                    msgEmptyList.visibility = View.GONE
                }

                override fun onNext(moviesList: MoviesList) {
                    success(moviesList.toList())
//                    val adapter = MoviesListAdapter(activity!!, listener, t.toList())
//                    moviesListView.adapter = adapter
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
//                    hideProgressBar()
//                    msgEmptyList.visibility = View.VISIBLE
                }

            })
    }
}