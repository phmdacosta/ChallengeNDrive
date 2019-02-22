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
                }

                override fun onNext(t: MoviesList) {
                    success(t.toList())
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
                }

            })
    }

    override fun getDetail(id: Long, success: (Movie) -> Unit, error: () -> Unit) : Disposable {
        return service.getMovieDetail(id, TMDbApiConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<Movie>() {
                override fun onComplete() {
                }

                override fun onNext(movie: Movie) {
                    success(movie)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
                }

            })
    }

    override fun search(query: String, success: (List<Movie>) -> Unit, error: () -> Unit) : Disposable {
        return service.searchMovie(TMDbApiConfig.API_KEY, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<MoviesList>() {
                override fun onComplete() {
                }

                override fun onNext(moviesList: MoviesList) {
                    success(moviesList.toList())
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error()
                }

            })
    }
}