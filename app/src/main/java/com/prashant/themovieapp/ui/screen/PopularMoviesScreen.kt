package com.prashant.themovieapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.prashant.themovieapp.data.model.PopularMovie
import com.prashant.themovieapp.ui.MainViewModel
import com.prashant.themovieapp.ui.theme.TheMovieAppTheme
import com.prashant.themovieapp.util.ApiResult
import com.prashant.themovieapp.util.Common


@Composable
fun PopularMoviesScreen(navController: NavController, mainViewModel: MainViewModel) {

    val movieApiResult by mainViewModel.popularMovieState

    LaunchedEffect(1) {
        mainViewModel.fetchPopularMovies()
    }

    Box {
        when (movieApiResult) {
            is ApiResult.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)
                ) {
                    items(items = (movieApiResult as ApiResult.Success<List<PopularMovie>>).value) {
                        MovieCard(navController, popularMovie = it)
                    }
                }
            }

            is ApiResult.Failure -> {
                ShowScreenMsg(msg = "Failed to load popular movies.")
            }

            is ApiResult.Loading -> {
                if ((movieApiResult as ApiResult.Loading).isLoading) {
                    ShowScreenMsg(msg = "Loading movies...")
                }
            }
        }
    }
}

@Composable
fun BoxScope.ShowScreenMsg(msg: String) {
    Text(
        text = msg,
        modifier = Modifier
            .padding(10.dp)
            .align(Alignment.Center),
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = TextUnit(20f, TextUnitType.Sp), color = MaterialTheme.colorScheme.primary
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(navController: NavController, popularMovie: PopularMovie) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        onClick = {
            navController.navigate(Screen.MovieDetailsScreen.withArgs(popularMovie.id.toString()))
        }
    ) {

        SubcomposeAsyncImage(
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(450.dp),
            contentScale = ContentScale.FillBounds,
            model = "${Common.POSTER_URL}${popularMovie.posterPath}",
            contentDescription = "Movie Poster"
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(450.dp)
                ) {
                    Text(
                        text = popularMovie.title,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = TextUnit(30f, TextUnitType.Sp),
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                }
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PopularMoviesScreenPreview() {
    TheMovieAppTheme {
        //PopularMoviesScreen()
    }
}