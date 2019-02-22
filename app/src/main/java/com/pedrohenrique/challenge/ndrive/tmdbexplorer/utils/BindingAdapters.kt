package com.pedrohenrique.challenge.ndrive.tmdbexplorer.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.adapters.MoviesListAdapterRecycle
import com.pedrohenrique.challenge.ndrive.tmdbexplorer.data.remote.TMDbApiConfig
import com.squareup.picasso.Picasso

class BindingAdapters {

    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(listView: RecyclerView, list: List<Any>) {
            val adapter = listView.adapter as MoviesListAdapterRecycle
            adapter.updateList(list)
        }

        @BindingAdapter("loadFrom")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Picasso.get().load(TMDbApiConfig.BASE_IMAGES_URL.plus(imageUrl))
                    .into(view)
        }
    }
}