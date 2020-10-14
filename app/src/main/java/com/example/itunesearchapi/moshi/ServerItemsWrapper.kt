package com.example.itunesearchapi.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T>(
    val resultCount: Int,
    val results: List<T>
)