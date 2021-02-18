package com.san.juan.app.movieapp.respository

import com.san.juan.app.movieapp.data.model.MovieList

/**
 * @author Dario Carrizo on 8/1/2021
 **/
interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}