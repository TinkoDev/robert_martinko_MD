package com.example.movieappmad23.utils

import android.content.Context
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.modelviews.ViewModelFactory
import com.example.movieappmad23.repo.MovieRepository

object InjectorUtils {
    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): ViewModelFactory {
        val repository = getMovieRepository(context)
        return ViewModelFactory(repository)
    }
}