package com.example.itunesearchapi.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumListViewModel: ViewModel(){

    private val repository = AlbumRepository()

    private val albumListLiveData = MutableLiveData<List<Search>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val albumList: LiveData<List<Search>>
        get() = albumListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    private val tostLiveData = MutableLiveData<String>()

    val toast: LiveData<String>
        get() = tostLiveData

    fun updateAlbum(search: List<Search>){
        val updateList = (search + albumListLiveData.value.orEmpty()).sortedBy { it.collectionName }
        albumListLiveData.postValue(updateList)

    }

    fun deleteAlbum(){
        albumListLiveData.postValue(emptyList())
    }

    fun search(query: String){
        deleteAlbum()
        isLoadingLiveData.postValue(true)
        repository.searchAlbum(
            query = query,
            onComplete = { search ->
                updateAlbum(search)
                isLoadingLiveData.postValue(false)
            },
            onError = { throwable ->
                isLoadingLiveData.postValue(false)
                albumListLiveData.postValue(emptyList())
                tostLiveData.postValue(throwable.message)
            }
        )
    }

}