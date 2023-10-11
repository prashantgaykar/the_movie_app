package com.prashant.themovieapp.data.repository

import com.prashant.themovieapp.data.remote.NetworkService
import com.prashant.themovieapp.di.TmdbApiKey

class MovieRepositoryImpl constructor(
    private val networkService: NetworkService,
    @TmdbApiKey private val tmdbApiKey: String
) : MovieRepository {

    override suspend fun getPopularMovies() = networkService.getPopularMovies(apiKey = tmdbApiKey)

    override suspend fun getMovieDetails(movieId: Long) =
        networkService.getMovieDetails(apiKey = tmdbApiKey, movieId = movieId)
}