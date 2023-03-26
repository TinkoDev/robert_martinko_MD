package com.example.mymovielist

import DetailScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningdiary2.screens.FavoriteScreen

import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
@ExperimentalMaterialApi
fun MyNavigation(){
        val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController) }
        composable("favoritescreen") { FavoriteScreen(navController = navController) }
        composable("detailscreen", arguments = listOf(navArgument("movieId"){}))

        { backStackEntry -> DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"),navController = navController) }
    }
}
