package com.example.itunesearchapi.retrofit

import com.example.itunesearchapi.moshi.Song

data class Search (
        val collectionId: String?,
        val collectionName: String?,
        val artworkUrl100: String?,
        val artistName: String?,
        val songList: List<Song>?
)