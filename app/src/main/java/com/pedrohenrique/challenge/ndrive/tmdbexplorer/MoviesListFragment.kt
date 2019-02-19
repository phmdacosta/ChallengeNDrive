package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesAdapterListener
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapter
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.TMDbService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movies_list.*

class MoviesListFragment : Fragment(), MoviesAdapterListener {

    companion object {
        const val SEARCH_KEY = "fragment_search_key"
    }

    private val listener = this
    private var disposables: CompositeDisposable = CompositeDisposable()
    private var searchQuery: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundle = arguments
        if (bundle != null) {
            searchQuery = bundle.getString(SEARCH_KEY)
        }

        return inflater.inflate(R.layout.movies_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        listMovies()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSelectListItem(movie: Movie) {
        val intent = Intent(activity!!, MovieDetailActivity::class.java)
        intent.putExtra(Movie.KEY, movie)
        startActivity(intent)
    }

    private fun listMovies() {
        val service = Service(activity!!).create(TMDbService::class.java)

        var observable = service.listMovies(getString(R.string.tmdb_api_key))
            .subscribeOn(Schedulers.io())

        if (searchQuery != null && !searchQuery!!.isEmpty()) {
            observable = service.searchMovie(getString(R.string.tmdb_api_key), searchQuery!!)
                .subscribeOn(Schedulers.io())
        }

        disposables.add(observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<MoviesList>() {
                override fun onComplete() {
                    hideProgressBar()
                    msgEmptyList.visibility = View.GONE
                }

                override fun onNext(t: MoviesList) {
                    val adapter = MoviesListAdapter(activity!!, listener, t.toList())
                    moviesListView.adapter = adapter
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    hideProgressBar()
                    msgEmptyList.visibility = View.VISIBLE
                }

            })
        )
    }

    private fun hideProgressBar() {
        moviesListProgressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}