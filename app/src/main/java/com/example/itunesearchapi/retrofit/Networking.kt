package com.example.itunesearchapi.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Networking {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClient())
        .build()

    val cbrApi: iTunesSearchApi
        get() = retrofit.create()

}