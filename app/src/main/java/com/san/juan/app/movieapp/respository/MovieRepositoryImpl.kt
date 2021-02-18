package com.san.juan.app.movieapp.respository

import com.san.juan.app.movieapp.data.model.MovieList
import com.san.juan.app.movieapp.data.remote.MovieDataSource

/**
 * @author Dario Carrizo on 8/1/2021
 **/
class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}