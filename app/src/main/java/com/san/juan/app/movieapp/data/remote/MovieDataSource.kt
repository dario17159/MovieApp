package com.san.juan.app.movieapp.data.remote

import com.san.juan.app.movieapp.application.AppConstants
import com.san.juan.app.movieapp.data.model.MovieList

/**
 * @author Dario Carrizo on 8/1/2021
 **/
class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}