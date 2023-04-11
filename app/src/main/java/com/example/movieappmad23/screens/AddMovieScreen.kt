
package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.viewmodels.ViewMovieModel
import com.example.movieappmad23.widgets.SimpleTopAppBar
import java.util.UUID


@Composable
fun AddMovieScreen(navController: NavController, viewMovieModel: ViewMovieModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), viewMovieModel = viewMovieModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, viewMovieModel: ViewMovieModel) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(true)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it
                    viewMovieModel.validateTitle(title) },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = viewMovieModel.titleError.value!!,


            )

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it
                                viewMovieModel.validateYear(year)},
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = viewMovieModel.yearError.value!!,
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }

                        }

                    ) {
                        Text(text = genreItem.title)
                    }

                }
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it
                                viewMovieModel.validateDirector(director)},
                label = { Text(stringResource(R.string.enter_director)) },
                isError = viewMovieModel.directorError.value!!,
            )

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it
                                viewMovieModel.validateActors(actors)},
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = viewMovieModel.actorsError.value!!,
            )

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = false
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                                rating = if(it.startsWith("0")) {
                                    ""
                                } else {
                                    it
                                }
                    viewMovieModel.validateRating(rating.toFloat())
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = viewMovieModel.ratingError.value!!,

            )

            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    val addedimage = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg")
                    val addedmovie = Movie(UUID.randomUUID().toString(), title, year, genres, director, actors, plot, addedimage, rating.toFloat())
                    viewMovieModel.addMovie(addedmovie)
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}