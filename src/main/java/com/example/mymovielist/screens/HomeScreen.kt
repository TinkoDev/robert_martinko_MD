package com.example.mymovielist

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.mymovielist.ui.theme.MyMovieListTheme
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies
import kotlinx.coroutines.channels.ticker
import kotlin.math.exp


@ExperimentalMaterialApi
@Composable
fun MyMovieList( movies: List<Movie> = getMovies(), navController: NavHostController){
    LazyColumn {
        items(items = movies) { movie ->
            MovieRow(movie = movie) {
                navController.navigate(route = "detailscreen/${movie.id}")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController) {
    var expandedState by remember { mutableStateOf(false) }

    Column{
            TopAppBar(
                title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { expandedState = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu Icon")
                    }
                    DropdownMenu(
                        expanded = expandedState,
                        onDismissRequest = { expandedState = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            navController.navigate(route = "favoritescreen")
                        }) {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favourites Icon DropDown")
                            Text(text = "Favorites")
                        }
                    }
                }
            )
        MyMovieList(navController = navController)
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if(expandedState) 180f else 0f)

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) }
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp,
        onClick = {
            expandedState = !expandedState
        }
    
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.images.first()),
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(modifier = Modifier
                    .weight(6f),
                    text = movie.title, style = MaterialTheme.typography.h6
                )
                
                IconButton( modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .weight(1f)
                    .rotate(rotationState),
                    onClick = {
                    expandedState = !expandedState
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Show details")
                }
            }
            if(expandedState) {
                Column(modifier = Modifier
                    .padding(10.dp),) {
                    Text (
                        text = "Genre: " + movie.genre
                    )
                    Text (
                        text = "Director: " + movie.director
                    )
                    Text (
                        text = "Actors: " + movie.actors
                    )
                    Text (
                        text = "Rating: " + movie.rating
                    )
                    Text (
                        text = "Release:" + movie.year
                    )
                    Text (
                        text = "-----------------"
                    )
                    Text (
                        text = "Plot: "
                    )
                    Text (
                        text = movie.plot
                    )
                }
            }
        }
    }
}

