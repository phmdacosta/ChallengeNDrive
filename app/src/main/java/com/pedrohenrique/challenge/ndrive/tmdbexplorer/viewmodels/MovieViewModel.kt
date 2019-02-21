package com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels

import android.content.Context
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.DataSource
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(val repository: TMDbRepository, val context: Context) {

    private var disposables: CompositeDisposable = CompositeDisposable()
}