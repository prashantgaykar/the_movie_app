package com.prashant.themovieapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.prashant.themovieapp.data.model.MovieDetails
import com.prashant.themovieapp.ui.MainViewModel
import com.prashant.themovieapp.ui.theme.TheMovieAppTheme
import com.prashant.themovieapp.util.ApiResult
import com.prashant.themovieapp.util.Common

@Composable
fun MovieDetailsScreen(movieId: String?, mainViewModel: MainViewModel) {

    val movieDetailsApiResult by mainViewModel.movieDetailsState

    LaunchedEffect(1) {
        mainViewModel.fetchMovieDetails(movieId?.toLong() ?: 0L)
    }

    Box {
        when (movieDetailsApiResult) {
            is ApiResult.Success -> {
                ShowMovieDetails(movieDetails = (movieDetailsApiResult as ApiResult.Success<MovieDetails>).value)
            }

            is ApiResult.Failure -> {
                ShowScreenMsg(msg = "Failed to load movie details.")
            }

            is ApiResult.Loading -> {
                if ((movieDetailsApiResult as ApiResult.Loading).isLoading) {
                    ShowScreenMsg(msg = "Loading movies details...")
                }
            }
        }
    }

}

@Composable
fun ShowMovieDetails(movieDetails: MovieDetails) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                AsyncImage(
                    model = "${Common.POSTER_URL}${movieDetails.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        item {
            Text(
                text = movieDetails.title,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = movieDetails.tagline,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movieDetails.releaseDate)
                Text(text = "${movieDetails.runtime} min")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movieDetails.genres.joinToString { it.name })
            }
        }
        item {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(
                text = movieDetails.overview,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailsScreenPreview() {
    TheMovieAppTheme {
        //MovieDetailsScreen()
    }
}

