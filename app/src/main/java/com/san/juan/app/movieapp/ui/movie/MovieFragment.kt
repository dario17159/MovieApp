package com.san.juan.app.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.san.juan.app.movieapp.R
import com.san.juan.app.movieapp.core.Resource
import com.san.juan.app.movieapp.data.model.Movie
import com.san.juan.app.movieapp.data.remote.MovieDataSource
import com.san.juan.app.movieapp.data.remote.RetrofitClient
import com.san.juan.app.movieapp.databinding.FragmentMovieBinding
import com.san.juan.app.movieapp.presentation.MovieViewModel
import com.san.juan.app.movieapp.presentation.MovieViewModelFactory
import com.san.juan.app.movieapp.respository.MovieRepositoryImpl
import com.san.juan.app.movieapp.ui.adapters.MovieAdapter
import com.san.juan.app.movieapp.ui.adapters.concat.PopularConcatAdapter
import com.san.juan.app.movieapp.ui.adapters.concat.TopRatedConcatAdapter
import com.san.juan.app.movieapp.ui.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var concatAdapter: ConcatAdapter

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    it.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    it.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    it.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("Error", "livedate ${it.exception}")
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie,imageView: ImageView) {

        val extras = FragmentNavigatorExtras(
            imageView to movie.poster_path
        )


        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            posterImageUrl = movie.poster_path,
            backgroundImageUrl = movie.backdrop_path,
            voteAverage = movie.vote_average.toFloat(),
            voteCount = movie.vote_count,
            overview = movie.overview,
            title = movie.title,
            language = movie.original_language,
            releaseDate = movie.release_date
        )

        findNavController().navigate(action,extras)
    }

}