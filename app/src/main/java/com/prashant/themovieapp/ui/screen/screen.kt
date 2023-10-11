package com.prashant.themovieapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prashant.themovieapp.ui.MainViewModel

sealed class Screen(val route: String) {
    object PopularMovieScreen : Screen(route = "popular_movie_screen")
    object MovieDetailsScreen : Screen(route = "movie_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}


@Composable
fun Navigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PopularMovieScreen.route) {
        composable(route = Screen.PopularMovieScreen.route) {
            PopularMoviesScreen(navController, mainViewModel)
        }
        composable(
            route = Screen.MovieDetailsScreen.route + "/{movie_id}",
            arguments = listOf(
                navArgument("movie_id") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ) { entry ->
            MovieDetailsScreen(movieId = entry.arguments?.getString("movie_id"), mainViewModel)
        }
    }
}