package com.example.itunesearchapi.retrofit.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesearchapi.retrofit.AlbumListViewModel
import com.example.itunesearchapi.R
import kotlinx.android.synthetic.main.fragment_album.*


class AlbumListFragment: Fragment(R.layout.fragment_album) {

    private var albumAdapter: AlbumAdapter by autoCleared()

    private val viewModel: AlbumListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList(){
        albumAdapter = AlbumAdapter{
                position ->
            val action = AlbumListFragmentDirections.actionAlbumListFragmentToDetailsFragment(
                position.toString(),
                albumAdapter.items.get(position).artworkUrl100.toString(),
                albumAdapter.items.get(position).songList.toString(),
                albumAdapter.items.get(position).artistName.toString(),
                albumAdapter.items.get(position).collectionName.toString()
            )
            findNavController().navigate(action)
        }
        with(recyclerview){
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        search_button.setOnClickListener {
            val queryText = search_text.text.toString()
            viewModel.search(queryText)
        }

        viewModel.isLoading.observe(viewLifecycleOwner,:: updateLoadingState)
        viewModel.albumList.observe(viewLifecycleOwner) {
            recyclerview.scrollToPosition(0)
            albumAdapter.items = it
        }
        viewModel.toast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }

    }

    private fun updateLoadingState(isLoading: Boolean){
        recyclerview.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
        search_button.isEnabled = isLoading.not()
        search_text.isEnabled = isLoading.not()
    }

}