package com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_item.view.*
import java.util.*

class MoviesListAdapter(context: Context, listener: MoviesAdapterListener, list: List<Movie>) : BaseAdapter() {

    private val listener = listener
    private var list = list
    private val context = context

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
        } else {
            holder.year.setTextColor(ContextCompat.getColor(context, R.color.list_item_subtitle))
            holder.year.setTypeface(null, Typeface.NORMAL)
        }

        Picasso.get().load(context.getString(R.string.tmdb_api_url_images).plus(movie.smallImgPosterPath))
            .into(holder.image)

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
        return list[position].id
    }

    override fun getCount(): Int {
        return list.size
    }

    fun updateList(list: List<Movie>) {
        this.list = list
        this.notifyDataSetChanged()
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