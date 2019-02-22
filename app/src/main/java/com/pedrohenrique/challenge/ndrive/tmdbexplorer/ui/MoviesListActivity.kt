package com.pedrohenrique.challenge.ndrive.tmdbexplorer.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import io.reactivex.disposables.CompositeDisposable

class MoviesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_page)
        loadListFragment(null)
    }

    private fun loadListFragment(searchQuery: String?) {
        val listShowsFragment: Fragment = MoviesListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerList, listShowsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
