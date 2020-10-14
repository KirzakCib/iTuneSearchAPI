package com.example.itunesearchapi.retrofit

import com.example.itunesearchapi.moshi.ServerItemsWrapper
import com.example.itunesearchapi.moshi.Album
import com.example.itunesearchapi.moshi.Artist
import com.example.itunesearchapi.moshi.Song
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesSearchApi {

    @GET("search?entity=allArtist&attribute=allArtistTerm")
    fun getValue(
        @Query("term") nameArtist : String
    ) : Call<ServerItemsWrapper<Artist>>

    @GET("lookup?entity=album")
    fun getAlbum(
        @Query("id") albumId : String
    ) : Call<ServerItemsWrapper<Album>>

    @GET("lookup?entity=song")
    fun getSong(
        @Query("id") songId : String
    ) : Call<ServerItemsWrapper<Song>>

}