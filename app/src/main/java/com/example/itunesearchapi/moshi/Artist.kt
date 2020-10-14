package com.example.itunesearchapi.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
    val artistName: String,
    val artistId: String
)