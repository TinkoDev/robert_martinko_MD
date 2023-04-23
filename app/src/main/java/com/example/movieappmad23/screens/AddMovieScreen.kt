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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.modelviews.ViewMovieModel
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch


@Composable
fun AddMovieScreen(navController: NavController, viewMovieModel : ViewMovieModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
       topBar = {
           SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
               Text(text = stringResource(id = R.string.add_movie))
           }
       }
    ) { padding ->
        MainContent(Modifier.padding(padding), viewMovieModel , navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, viewMovieModel : ViewMovieModel, navController: NavController) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        val viewModel: ViewMovieModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
            LocalContext.current))
        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = viewMovieModel.title.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewMovieModel.title.value = it
                    viewMovieModel.validateTitle()
                                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = viewMovieModel.isTitleValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.titleErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(
                value = viewMovieModel.year.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewMovieModel.year.value = it; viewMovieModel.validateYear() },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = viewMovieModel.isYearValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.yearErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)


            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(viewMovieModel.selectedGenres.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            viewMovieModel.selectedGenres.value = viewMovieModel.selectedGenres.value.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            viewMovieModel.validateGenres()
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.genresErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(
                value = viewMovieModel.director.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewMovieModel.director.value = it; viewMovieModel.validateDirector() },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = viewMovieModel.isDirectorValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.directorErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(
                value = viewMovieModel.actors.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { viewMovieModel.actors.value = it; viewMovieModel.validateActors() },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = viewMovieModel.isActorsValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.actorsErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(
                value = viewMovieModel.plot.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { viewMovieModel.plot.value = it; viewMovieModel.validatePlot() },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = viewMovieModel.isPlotValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.plotErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            OutlinedTextField(
                value = viewMovieModel.rating.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewMovieModel.rating.value = if(it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                    viewMovieModel.validateRating()
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = viewMovieModel.isRatingValid.value
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = viewMovieModel.ratingErrMsg.value,
                fontSize = 14.sp,
                color = Color.Red
            )

            Button(
                enabled = viewMovieModel.isEnabledAddMovieButton.value,
                onClick = { coroutineScope.launch {
                    viewMovieModel.addMovie(navController);
                }}) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}