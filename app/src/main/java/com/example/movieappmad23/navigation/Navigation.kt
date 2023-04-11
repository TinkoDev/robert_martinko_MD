package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.screens.*
import com.example.movieappmad23.viewmodels.ViewMovieModel

@Composable
fun Navigation(viewMovieModel: ViewMovieModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, viewMovieModel = viewMovieModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, viewMovieModel = viewMovieModel)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, viewMovieModel = viewMovieModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController, backStackEntry.arguments?.getString(
                DETAIL_ARGUMENT_KEY), viewMovieModel = viewMovieModel)   // get the argument from navhost that will be passed
        }
    }
}