package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R.id.moviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesAdapterListener
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapter
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.MoviesList
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.Service
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.services.TMDbService
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity(), MoviesAdapterListener {

    private val TAG = MoviesListActivity::class.java.simpleName

    private val listener = this
    //    private var adapter: MoviesListAdapter? = null
    var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        listMovies()
    }

//    private fun initListShowsFragment() {
//        val listShowsFragment: Fragment = MoviesListFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.containerList, listShowsFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    private fun listMovies() {
//        val strings = arrayOf("Teste01", "Teste02", "Teste03")
//        val adapter = ArrayAdapter(this, R.layout.teste, strings)
//        val listView = findViewById<ListView>(R.id.moviesList)
//        listView.adapter = adapter

//        val list = ArrayList<Movie>()
//        val movie = Movie()
//        movie.id = 1
//        movie.title = "Teste 01"
//        val movie2 = Movie()
//        movie2.id = 2
//        movie2.title = "Teste 02"
//        val movie3 = Movie()
//        movie3.id = 3
//        movie3.title = "Teste 03"
//        list.add(movie)
//        list.add(movie2)
//        list.add(movie3)
//        val adapter = MoviesListAdapter(listener, list)
//        val listView = findViewById<ListView>(R.id.moviesList)
//        listView?.adapter = adapter
//        adapter.notifyDataSetChanged()

        val service = Service(this).create(TMDbService::class.java)
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

        val call = service.listMovies(getString(R.string.tmdb_api_key))
        call.enqueue(object: Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                val list = response.body()!!.toList()
                val adapter = MoviesListAdapter(listener, list)
//                val listView = findViewById<ListView>(R.id.moviesList)
                moviesList.adapter = adapter
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                t.printStackTrace()
                // Do nothing
            }
        })
    }

    override fun onSelectListItem(movie: Movie) {
        openMovieDetail(movie.id)
    }

    private fun openMovieDetail(id: Long) {

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
