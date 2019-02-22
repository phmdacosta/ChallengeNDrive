package com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.widget.ListView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import java.lang.Exception

class MoviesListViewModel(val repository: TMDbRepository) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()
    val list = ObservableArrayList<Movie>()
    val loading = ObservableBoolean(false)
    val showEmptyListMessage = ObservableBoolean(false)

    fun listMovies() {
        loading.set(true)
        disposables.add(repository.list(
            { list ->
                loading.set(false)
                if (list.isEmpty()) {
                    showEmptyListMessage.set(true)
                }
                this.list.clear()
                this.list.addAll(list)
            },
            {
                loading.set(false)
                showEmptyListMessage.set(true)
            }))
    }

    fun searchMovie(query: String) {
        loading.set(true)
        disposables.add(repository.search(query,
            { list ->
                loading.set(false)
                if (list.isEmpty()) {
                    showEmptyListMessage.set(true)
                }
                this.list.clear()
                this.list.addAll(list)
            },
            {
                loading.set(false)
                showEmptyListMessage.set(true)
            }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}