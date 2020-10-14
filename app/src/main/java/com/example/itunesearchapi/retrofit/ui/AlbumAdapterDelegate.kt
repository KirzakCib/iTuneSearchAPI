package com.example.itunesearchapi.retrofit.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunesearchapi.R
import com.example.itunesearchapi.retrofit.Search
import com.example.itunesearchapi.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class AlbumAdapterDelegate(
    private val onItemClicked: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Search, Search, AlbumAdapterDelegate.Holder>() {

    override fun isForViewType(item: Search, items: MutableList<Search>, position: Int): Boolean {
        return item is Search
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
       return Holder(parent.inflate(R.layout.item_album),onItemClicked)
    }

    override fun onBindViewHolder(item: Search, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        view: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById(R.id.name)
        private val imageView: ImageView = view.findViewById(R.id.image)
        private val artistTextView: TextView = view.findViewById(R.id.artist)

        init {
            view.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(album: Search){
            artistTextView.text = album.artistName
            nameTextView.text = album.collectionName
            Glide.with(itemView)
                .load(album.artworkUrl100)
                .error(R.mipmap.ic_launcher)
                .into(imageView)
        }
    }

}