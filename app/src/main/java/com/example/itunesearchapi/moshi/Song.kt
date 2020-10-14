package com.example.itunesearchapi.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Song(
    val trackName: String?
//    val trackTimeMillis: Long?,
//    val collectionId: String?
)