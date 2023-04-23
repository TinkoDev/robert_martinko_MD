package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.modelviews.ViewMovieModel
import com.example.movieappmad23.widgets.HomeTopAppBar
import com.example.movieappmad23.widgets.MovieRow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewMovieModel: ViewMovieModel){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddMovieScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Movie", modifier = Modifier.padding(4.dp))
                        Text(text = "Add Movie", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
                DropdownMenuItem(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp))
                        Text(text = "Favorites", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContent(modifier = Modifier.padding(padding), navController = navController, movieModel = viewMovieModel)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    movieModel: ViewMovieModel
) {
    val moviesState by movieModel.movieList.collectAsState()
    //val movies = getMovies()
    MovieList(
        navController = navController,
        movies = moviesState,
        movieModel = movieModel

    )
}


@Composable
fun MovieList(
    navController: NavController,
    movieModel: ViewMovieModel,
    movies: List<Movie>
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn {
        items(movies) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { navController.navigate("detailscreen/${movie.id}") },
                onFavoriteClick = {  coroutineScope.launch {
                    movieModel.toggleFavorite(movie)
                }}
            )
        }
    }
}


