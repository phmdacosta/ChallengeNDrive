package com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.R
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbApiConfig
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.databinding.MoviesListItemBinding
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list_item.view.*
import java.util.*

class MoviesListAdapterRecycle(private val context: Context, private val listener: MoviesAdapterListener, private var list: List<Movie>) : RecyclerView.Adapter<MoviesListAdapterRecycle.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MoviesListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(context, binding, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    @Suppress("UNCHECKED_CAST")
    fun updateList(list: List<*>) {
        this.list = list as List<Movie>
        this.notifyDataSetChanged()
    }

    class ViewHolder(val context: Context, val binding: MoviesListItemBinding, val listener: MoviesAdapterListener) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                titleMovieItem.text = movie.title

                val calendar = Calendar.getInstance()
                val currentYear = calendar.get(Calendar.YEAR)
                yearMovieItem.text = movie.releaseYear.toString()

                if (yearMovieItem.text == currentYear.toString()) {
                    yearMovieItem.setTextColor(Color.RED)
                    yearMovieItem.setTypeface(null, Typeface.BOLD)
                } else {
                    yearMovieItem.setTextColor(ContextCompat.getColor(context, R.color.list_item_subtitle))
                    yearMovieItem.setTypeface(null, Typeface.NORMAL)
                }

                Picasso.get().load(TMDbApiConfig.BASE_IMAGES_URL.plus(movie.smallImgPosterPath))
                    .into(imgMovieItem)

                movieItem.setOnClickListener {
                    listener.onSelectListItem(movie)
                }
            }
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}

interface MoviesAdapterListener {
    fun onSelectListItem(movie: Movie)
}