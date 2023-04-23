package com.example.movieappmad23.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieappmad23.modelviews.ViewMovieModel
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    movieViewModel: ViewMovieModel
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = "My Favorite Movies")
            }
        },
        content = { padding ->
            Log.d("Padding Values", "$padding")
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    val favoriteMovies = movieViewModel.getFavoriteMovies()

                    MovieList(navController = navController, movies = favoriteMovies, movieModel = movieViewModel)
                }
            }
        }
    )
}