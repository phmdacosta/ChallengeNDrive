package com.pedrohenrique.challenge.ndrive.tmdbexplorer.data
import io.reactivex.disposables.Disposable

interface DataSource<T> {
    fun list(success : (List<T>) -> Unit, error: () -> Unit) : Disposable

    fun getDetail(id: Long, success : (T) -> Unit, error: () -> Unit) : Disposable

    fun search(query: String, success : (List<T>) -> Unit, error: () -> Unit) : Disposable
}