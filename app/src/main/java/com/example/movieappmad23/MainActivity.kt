package com.example.movieappmad23

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieappmad23.navigation.Navigation
import com.example.movieappmad23.ui.theme.MovieAppMAD23Theme
import com.example.movieappmad23.viewmodels.ViewMovieModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieModel: ViewMovieModel by viewModels()
        setContent {
            MovieAppMAD23Theme {
                Navigation(movieModel)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }
}

