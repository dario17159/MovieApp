package com.san.juan.app.movieapp.ui.moviedetails

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.san.juan.app.movieapp.R
import com.san.juan.app.movieapp.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.posterImageUrl}")
            .centerCrop()
            .into(binding.imgMovie)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.backgroundImageUrl}")
            .centerCrop()
            .into(binding.imgBackground)

        binding.txtDescription.text = args.overview

        binding.txtMovieTitle.text = args.title
        binding.txtLanguage.text = String.format("Language %s",args.language)
        binding.txtRating.text = String.format("%s (%s Reviews)",args.voteAverage,args.voteCount)
        binding.txtRelease.text = String.format("Released: %s", args.releaseDate)
       /* binding.imgMovie.apply{
            transitionName = args.posterImageUrl
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500${args.posterImageUrl}")
                .centerCrop()
                .into(this)
        }*/
    }
}