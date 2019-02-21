package com.pedrohenrique.challenge.ndrive.tmdbexplorer.data

import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import io.reactivex.disposables.Disposable

class TMDbRepository(service: TMDbService) : DataSource<Movie> {
    private val dataSource = TMDbDataSource(service)

    override fun list(success: (List<Movie>) -> Unit, error: () -> Unit): Disposable {
        return dataSource.list(success, error)
    }

    override fun getDetail(id: Long, success: (Movie) -> Unit, error: () -> Unit): Disposable {
        return dataSource.getDetail(id, success, error)
    }

    override fun search(query: String, success: (List<Movie>) -> Unit, error: () -> Unit): Disposable {
        return dataSource.search(query, success, error)
    }
}