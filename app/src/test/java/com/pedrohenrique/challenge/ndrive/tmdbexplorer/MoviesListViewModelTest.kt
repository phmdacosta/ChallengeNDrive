package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels.MoviesListViewModel
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MoviesListViewModelTest {
    private lateinit var movieTest: Movie

    @Before
    fun setup() {
        movieTest = Movie()
        movieTest.title = Mockito.anyString()
        movieTest.releaseDate = Mockito.any(Date::class.java)
        movieTest.runtime = 130
    }

    @Test
    fun validRuntimFormat() {
        assert(movieTest.runtime.equals("2h 10m"))
    }
}
