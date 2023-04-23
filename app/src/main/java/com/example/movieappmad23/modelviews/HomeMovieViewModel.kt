/*
package com.example.movieappmad23.modelviews


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repo.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getFavoriteMovies().collect { x -> _movies.value = x }
        }
    }

    suspend fun toggleFavorite(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        movieRepository.updateMovie(movie)
    }
}
*/