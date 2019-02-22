package com.pedrohenrique.challenge.ndrive.tmdbexplorer.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesAdapterListener
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapterRecycle
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.TMDbRepository
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbService
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.databinding.MoviesListBinding
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.viewmodels.MoviesListViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.movies_list.*

class MoviesListFragment : Fragment(), MoviesAdapterListener {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var binding: MoviesListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = MoviesListBinding.inflate(inflater, container, false)
        binding.moviesRecyclerView.adapter = MoviesListAdapterRecycle(activity!!, this, ArrayList<Movie>())
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(activity)

        return binding.root
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
        val service = Service().create(TMDbService::class.java)
        val repository = TMDbRepository(service)
        viewModel = MoviesListViewModel(repository)
        binding.moviesListViewModel = viewModel
        viewModel.listMovies()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}