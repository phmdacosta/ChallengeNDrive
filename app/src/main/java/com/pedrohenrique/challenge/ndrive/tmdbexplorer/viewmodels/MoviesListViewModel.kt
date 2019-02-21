package com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.ListView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

class MoviesListViewModel(val repository: TMDbRepository, val context: Context) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()

    fun listMovies(observer: DisposableObserver<List<Movie>>) {
        disposables.add(repository.list(
            { list ->
                if (list.isEmpty()) {
                    observer.onError(Exception())
                }
                observer.onComplete()
                observer.onNext(list)
            },
            {
                observer.onError(Exception())
            }))
    }

    fun searchMovie(query: String, observer: DisposableObserver<List<Movie>>) {
        disposables.add(repository.search(query,
            { list ->
                if (list.isEmpty()) {
                    observer.onError(Exception())
                }
                observer.onComplete()
                observer.onNext(list)
            },
            {
                observer.onError(Exception())
            }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}