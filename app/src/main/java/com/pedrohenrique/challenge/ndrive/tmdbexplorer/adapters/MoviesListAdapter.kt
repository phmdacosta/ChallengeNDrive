package com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import kotlinx.android.synthetic.main.movies_list_item.view.*
import java.util.*

class MoviesListAdapter(listener: MoviesAdapterListener, list: List<Movie>) : BaseAdapter() {

    private val listener = listener
    private var list = list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        var holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.movies_list_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val movie: Movie = getItem(position) as Movie
        holder.title.text = movie.title

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        holder.year.text = movie.releaseYear.toString()

        if (holder.year.text == currentYear.toString()) {
            holder.year.setTextColor(Color.RED)
            holder.year.setTypeface(null, Typeface.BOLD)
        }

//        Picasso.get().load(Movie.image?.medium)
//            .placeholder(R.drawable.ic_placeholder)
//            .into(holder.image)

        holder.layoutItem.setOnClickListener {
            listener.onSelectListItem(movie)
        }

        Log.i("TESTE", view.titleMovieItem.text.toString())

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
//        return list[position].id
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    private class ViewHolder(view: View) {
        var layoutItem: LinearLayout = view.findViewById(R.id.movieItem)
        var image: ImageView = view.findViewById(R.id.imgMovieItem)
        var title: TextView = view.findViewById(R.id.titleMovieItem)
        var year: TextView = view.findViewById(R.id.yearMovieItem)
    }
}

interface MoviesAdapterListener {
    fun onSelectListItem(movie: Movie)
}