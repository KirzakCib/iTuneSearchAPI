package com.example.itunesearchapi.retrofit

import com.example.itunesearchapi.moshi.Album
import com.example.itunesearchapi.moshi.Artist
import com.example.itunesearchapi.moshi.ServerItemsWrapper
import com.example.itunesearchapi.moshi.Song
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class AlbumRepository {

    fun searchAlbum(
        query: String,
        onComplete: (List<Search>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (query.equals("")) {
            onComplete(emptyList())
        } else {
            Networking.cbrApi.getValue(query).enqueue(
                object : Callback<ServerItemsWrapper<Artist>> {
                    override fun onResponse(
                        call: Call<ServerItemsWrapper<Artist>>,
                        response: Response<ServerItemsWrapper<Artist>>
                    ) {
                        if (response.isSuccessful) {
                            if(response.body()?.resultCount == 0)
                                onError(RuntimeException("Not found"))
                            val artist = response.body()?.results.orEmpty()
                            for (artist_ in artist) {
                                Networking.cbrApi.getAlbum(artist_.artistId).enqueue(
                                    object : Callback<ServerItemsWrapper<Album>> {
                                        override fun onResponse(
                                            call: Call<ServerItemsWrapper<Album>>,
                                            response: Response<ServerItemsWrapper<Album>>
                                        ) {
                                            if (response.isSuccessful) {
                                                val album = response.body()?.results.orEmpty()
                                                    .subList(
                                                        1,
                                                        response.body()?.results.orEmpty().size
                                                    )
                                                for (album_ in album) {
                                                    Networking.cbrApi.getSong(album_.collectionId.toString())
                                                        .enqueue(
                                                            object :
                                                                Callback<ServerItemsWrapper<Song>> {
                                                                override fun onResponse(
                                                                    call: Call<ServerItemsWrapper<Song>>,
                                                                    response: Response<ServerItemsWrapper<Song>>
                                                                ) {
                                                                    if (response.isSuccessful) {
                                                                        val song =
                                                                            response.body()?.results
                                                                                ?.subList(
                                                                                    1,
                                                                                    response.body()?.results.orEmpty().size
                                                                                ).orEmpty()
                                                                        val search: List<Search> =
                                                                            listOf(
                                                                                Search(
                                                                                    collectionId = album_.collectionId,
                                                                                    collectionName = album_.collectionName,
                                                                                    artworkUrl100 = album_.artworkUrl100,
                                                                                    artistName = album_.artistName,
                                                                                    songList = song
                                                                                )
                                                                            )
                                                                        onComplete(search)
                                                                    }
                                                                }

                                                                override fun onFailure(
                                                                    call: Call<ServerItemsWrapper<Song>>,
                                                                    t: Throwable
                                                                ) {
                                                                    onError (RuntimeException ("Error, check your network connection. Regards."))
                                                                }

                                                            }
                                                        )
                                                }
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<ServerItemsWrapper<Album>>,
                                            t: Throwable
                                        ) {
                                            onError (RuntimeException ("Error, check your network connection. Regards."))
                                        }

                                    }
                                )
                            }
                        }else {
                            onError(RuntimeException("Not found"))
                        }
                    }

                    override fun onFailure(call: Call<ServerItemsWrapper<Artist>>, t: Throwable) {
                        onError (RuntimeException ("Error, check your network connection. Regards."))
                    }

                }
            )
        }
    }
}


