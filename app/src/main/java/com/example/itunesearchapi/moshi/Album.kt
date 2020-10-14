package com.example.itunesearchapi.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album(
    val collectionName: String?,
    val artworkUrl100: String?,
    val artistName: String?,
    val collectionId: String?
)