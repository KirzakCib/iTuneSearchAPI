package com.example.itunesearchapi.retrofit.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.itunesearchapi.R
import kotlinx.android.synthetic.main.fragment_details_album.*

class DetailsAlbumFragment : Fragment(R.layout.fragment_details_album){

    private val args: DetailsAlbumFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(artworkUrl100)
            .load(args.artworkUrl100)
            .error(R.mipmap.ic_launcher)
            .into(artworkUrl100)

        var textSong = args.song.substring(0,args.song.length-2).replace("[Song(trackName","")

        val str = textSong.split("=")

        var id = 1
        for(str_ in str) {
            song.append("$id)" + str_.replace("Song(trackName","").replace("),","") + "\n")
            id++
        }

        collectionName.text = "name album: " + args.collectionName
        artistName.text = "name artist: " + args.artistName

    }

}