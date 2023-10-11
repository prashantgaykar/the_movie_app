package com.prashant.themovieapp.data.remote

import com.prashant.themovieapp.data.model.MovieDetails
import com.prashant.themovieapp.data.remote.response.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
    ): MovieDetails
}