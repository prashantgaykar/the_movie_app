package com.prashant.themovieapp.data.repository

import com.prashant.themovieapp.data.model.MovieDetails
import com.prashant.themovieapp.data.remote.response.PopularMoviesResponse

interface MovieRepository {
    suspend fun getPopularMovies(): PopularMoviesResponse
    suspend fun getMovieDetails(movieId: Long): MovieDetails
}