package com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.DataSource
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(val repository: TMDbRepository) : ViewModel() {

    private var disposables: CompositeDisposable = CompositeDisposable()
    val title = ObservableField<String>("")
    val imgBackdropPath = ObservableField<String>("")
    val overview = ObservableField<String>("")
    val revenue = ObservableField<String>("")
    val runtime = ObservableField<String>("")
    val showErrorMessage = ObservableBoolean(false)
    val loading = ObservableBoolean(false)

    fun getMovieDetail(id: Long) {
        loading.set(true)
        disposables.add(repository.getDetail(id,
            { movie ->
                loading.set(false)
                title.set(movie.title)
                imgBackdropPath.set(movie.bigImgBackdropPath)
                overview.set(movie.overview)
                revenue.set(movie.revenueFormated)
                runtime.set(movie.runtimeFormated)
            },
            {
                loading.set(false)
                showErrorMessage.set(true)
            }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}