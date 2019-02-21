package com.pedrohenrique.challenge.ndrive.tmdbexplorer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.search.*

class MoviesListActivity : AppCompatActivity() {

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        loadListFragment(null)
    }

    private fun loadListFragment(searchQuery: String?) {
        val listShowsFragment: Fragment = MoviesListFragment()

        val bundle = Bundle()
        bundle.putString(MoviesListFragment.SEARCH_KEY, searchQuery)
        listShowsFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerList, listShowsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun searchMovie(view: View) {
        loadListFragment(searchEditText.text.toString())
//        val service = Service(this).create(TMDbService::class.java)
//        disposables.add(service.searchMovie(getString(R.string.tmdb_api_key), searchEditText.text.toString())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object: DisposableObserver<MoviesList>() {
//                override fun onComplete() {
//                }
//                override fun onNext(moviesList: MoviesList) {
//                    loadListFragment()
//                }
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    msgEmptyList.visibility = View.VISIBLE
//                }
//
//            })
//        )
    }
}
