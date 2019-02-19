package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesAdapterListener
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapter
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.TMDbService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListFragment : Fragment(), MoviesAdapterListener {

    private val TAG = MoviesListActivity::class.java.simpleName

    private val listener = this
//    private var adapter: MoviesListAdapter? = null
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        listMovies()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSelectListItem(movie: Movie) {
        openMovieDetail(movie.id)
    }

    private fun openMovieDetail(id: Long) {

    }

    private fun listMovies() {
//        val movie = Movie()
//        movie.title = "Teste 01"
//        val list = ArrayList<Movie>()
//        list.add(movie)
//        val adapter = MoviesListAdapter(listener, list)
//        val listView = activity?.findViewById<ListView>(R.id.moviesList)
//        listView?.adapter = adapter
//        adapter.notifyDataSetChanged()

        val service = Service(activity!!).create(TMDbService::class.java)
//        disposables.add(service.listMovies(getString(R.string.tmdb_api_key))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object: DisposableObserver<MoviesList>() {
//                override fun onComplete() {
//                    Log.d(TAG, "In onCompleted()")
//                }
//
//                override fun onNext(t: MoviesList) {
//                    Log.d(TAG, "In onNext()")
//                    val adapter = MoviesListAdapter(listener, t.toList())
//                    val listView = activity?.findViewById<ListView>(R.id.moviesList)
//                    listView?.adapter = adapter
//                }
//
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    Log.d(TAG, "In onError()")
//                }
//
//            })
//        )

//        val call = service.listMovies(getString(R.string.tmdb_api_key))
//        call.enqueue(object: Callback<MoviesList> {
//            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
//                val list = response.body()!!.toList()
//                val adapter = MoviesListAdapter(listener, list)
//                val listView = activity?.findViewById<ListView>(R.id.moviesList)
//                listView?.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
//                t.printStackTrace()
//                // Do nothing
//            }
//        })
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}