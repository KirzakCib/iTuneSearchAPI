package com.example.itunesearchapi.retrofit.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.itunesearchapi.retrofit.Search
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AlbumAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Search>(AlbumDiffUtilCallback()){

    init{
        delegatesManager.addDelegate(AlbumAdapterDelegate(onItemClicked))
    }

    class AlbumDiffUtilCallback: DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

    }

}