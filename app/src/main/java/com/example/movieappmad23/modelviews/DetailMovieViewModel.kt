/*
package com.example.movieappmad23.modelviews


import androidx.lifecycle.ViewModel
import com.example.movieappmad23.repo.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    suspend fun getMovieById(id: String) {
        return movieRepository.getMovieById(id).collect{ it }
    }
}
*/