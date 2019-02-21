package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesAdapterListener
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapter
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels.MoviesListViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
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
    private lateinit var viewModel: MoviesListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundle = arguments
        if (bundle != null) {
            searchQuery = bundle.getString(SEARCH_KEY)
        }

        return inflater.inflate(R.layout.movies_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if (searchQuery != null && !searchQuery!!.isEmpty()) {
            searchMovies()
        } else {
            listMovies()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSelectListItem(movie: Movie) {
        val intent = Intent(activity!!, MovieDetailActivity::class.java)
        intent.putExtra(Movie.KEY, movie)
        startActivity(intent)
    }

    private fun listMovies() {
        val service = Service().create(TMDbService::class.java)
        val repository = TMDbRepository(service)
        viewModel = MoviesListViewModel(repository, activity!!)
        viewModel.listMovies(object: DisposableObserver<List<Movie>>() {
            override fun onComplete() {
                hideProgressBar()
                msgEmptyList.visibility = View.GONE
            }

            override fun onNext(list: List<Movie>) {
                val adapter = MoviesListAdapter(activity!!, listener, list.toList())
                moviesListView.adapter = adapter
            }

            override fun onError(e: Throwable) {
                hideProgressBar()
                msgEmptyList.visibility = View.VISIBLE
            }

        })
    }

    private fun searchMovies() {
        val service = Service().create(TMDbService::class.java)
        val repository = TMDbRepository(service)
        viewModel = MoviesListViewModel(repository, activity!!)
        viewModel.searchMovie(searchQuery!!)
    }

    private fun hideProgressBar() {
        moviesListProgressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}