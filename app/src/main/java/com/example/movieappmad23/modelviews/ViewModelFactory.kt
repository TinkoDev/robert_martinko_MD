package com.example.movieappmad23.modelviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad23.repo.MovieRepository

class ViewModelFactory (private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewMovieModel::class.java)){
            return ViewMovieModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}