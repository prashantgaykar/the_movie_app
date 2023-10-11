package com.prashant.themovieapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.themovieapp.data.model.MovieDetails
import com.prashant.themovieapp.data.model.PopularMovie
import com.prashant.themovieapp.data.repository.MovieRepository
import com.prashant.themovieapp.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovieState =
        mutableStateOf<ApiResult<List<PopularMovie>>>(ApiResult.loading(false))
    val popularMovieState = _popularMovieState as State<ApiResult<List<PopularMovie>>>

    private val _movieDetailsState =
        mutableStateOf<ApiResult<MovieDetails>>(ApiResult.loading(false))
    val movieDetailsState = _movieDetailsState as State<ApiResult<MovieDetails>>

    fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                _popularMovieState.value = ApiResult.loading(true)
                _popularMovieState.value =
                    ApiResult.success(movieRepository.getPopularMovies().results.take(10))
            } catch (e: Exception) {
                Log.e("MovieApp", "MainViewModel Exception - $e")
                _popularMovieState.value = ApiResult.failure(e)
            }
        }
    }

    fun fetchMovieDetails(movieId: Long) {
        viewModelScope.launch {
            try {
                _movieDetailsState.value = ApiResult.loading(true)
                _movieDetailsState.value =
                    ApiResult.success(movieRepository.getMovieDetails(movieId))
            } catch (e: Exception) {
                Log.e("MovieApp", "MainViewModel Exception - $e")
                _movieDetailsState.value = ApiResult.failure(e)
            }
        }
    }
}