package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.screens.*
import com.example.movieappmad23.modelviews.ViewMovieModel
import com.example.movieappmad23.utils.InjectorUtils

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: ViewMovieModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, viewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, viewModel)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, viewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->
            // backstack contains all information from navhost
            DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"),navController = navController, viewModel) }
            // get the argument from navhost that will be passed
        }
    }