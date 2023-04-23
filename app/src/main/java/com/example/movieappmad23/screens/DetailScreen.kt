package com.example.movieappmad23.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappmad23.modelviews.ViewMovieModel
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.widgets.MovieRow



@Composable
fun DetailScreen(
    movieId: String?,
    navController: NavHostController,
    viewMovieModel: ViewMovieModel
) {
    val viewModel: ViewMovieModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    val selectedMovie = movieId?.let {  viewMovieModel.getSelectedMovie(movieId) }
    Scaffold(
        topBar = {
            SimpleAppBar(
                title = selectedMovie?.title,
                onBackPressed = { navController.popBackStack() })
        },
        content = { padding ->
            Log.d("Padding Values", "$padding")
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (movieId != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {

                        if (selectedMovie != null) {
                            MovieRow(movie = selectedMovie) {
                                Log.d("DetailScreen", "PosterClicked");
                            }
                        }
                        Text(
                            text = "Images",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                        ) {
                            if (selectedMovie != null) {
                                items(items = selectedMovie.images) { movieImage ->
                                    Card(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = 3.dp
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(model = movieImage),
                                            contentDescription = "Movie Image",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun SimpleAppBar(
    title: String?,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            if (title != null) {
                Text(text = title)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}