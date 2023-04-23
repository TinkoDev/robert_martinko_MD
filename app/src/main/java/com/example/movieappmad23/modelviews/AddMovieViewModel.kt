/*package com.example.movieappmad23.modelviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repo.MovieRepository

class AddMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    suspend fun addMovie (movie: Movie){
        movieRepository.insertMovie(movie)
    }

    private var _titleError = MutableLiveData<Boolean>(true)
    var titleError: LiveData<Boolean>
        get() = _titleError
        set(value) { _titleError.value = value.value }

    private val _titleErrorMessage = MutableLiveData<String>()
    val titleErrorMessage: LiveData<String>
        get() = _titleErrorMessage

    private var _yearError = MutableLiveData<Boolean>(true)
    var yearError: LiveData<Boolean>
        get() = _yearError
        set(value) { _yearError.value = value.value }

    private val _yearErrorMessage = MutableLiveData<String>()
    val yearErrorMessage: LiveData<String>
        get() = _yearErrorMessage

    private var _genreError = MutableLiveData<Boolean>(true)
    var genreError: LiveData<Boolean>
        get() = _genreError
        set(value) { _genreError.value = value.value }

    private val _genreErrorMessage = MutableLiveData<String>()
    val genreErrorMessage: LiveData<String>
        get() = _genreErrorMessage

    private var _directorError = MutableLiveData<Boolean>(true)
    var directorError: LiveData<Boolean>
        get() = _directorError
        set(value) { _directorError.value = value.value }

    private val _directorErrorMessage = MutableLiveData<String>()
    val directorErrorMessage: LiveData<String>
        get() = _directorErrorMessage

    private var _actorsError = MutableLiveData<Boolean>(true)
    var actorsError: LiveData<Boolean>
        get() = _actorsError
        set(value) { _actorsError.value = value.value }

    private val _actorsErrorMessage = MutableLiveData<String>()
    val actorsErrorMessage: LiveData<String>
        get() = _actorsErrorMessage

    private var _ratingError = MutableLiveData<Boolean>(true)
    var ratingError: LiveData<Boolean>
        get() = _ratingError
        set(value) { _ratingError.value = value.value }

    private val _ratingErrorMessage = MutableLiveData<String>()
    val ratingErrorMessage: LiveData<String>
        get() = _ratingErrorMessage

    private var _isAddButtonEnabled = MutableLiveData<Boolean>(false)
    var isAddButtonEnabled: LiveData<Boolean>
        get() = _isAddButtonEnabled
        set(value) { _isAddButtonEnabled.value = value.value }

    fun validateTitle(title: String) {
        if (title.isEmpty()) {
            _titleErrorMessage.value = "Title cannot be empty"
            titleError = MutableLiveData(true)
        } else {
            titleError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    fun validateYear(year: String) {
        if (year.isEmpty()) {
            _yearErrorMessage.value = "Year cannot be empty"
            yearError = MutableLiveData(true)
        } else {
            yearError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    fun validateGenres(genres: List<ListItemSelectable>) {
        if (genres.isEmpty()) {
            _genreErrorMessage.value = "At least one genre must be selected"
            genreError = MutableLiveData(true)
        } else {
            genreError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    fun validateDirector(director: String) {
        if (director.isEmpty()) {
            _directorErrorMessage.value = "Director cannot be empty"
            directorError = MutableLiveData(true)
        } else {
            directorError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    fun validateActors(actors: String) {
        if (actors.isEmpty()) {
            _actorsErrorMessage.value = "Actors cannot be empty"
            actorsError = MutableLiveData(true)
        } else {
            actorsError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    fun validateRating(rating: Float) {
        if (rating <= 0) {
            _ratingErrorMessage.value = "Rating must be greater than 0"
            ratingError = MutableLiveData(true)
        } else {
            ratingError = MutableLiveData(false)
        }
        updateAddButtonState()
    }

    private fun updateAddButtonState() {
        // TODO: add genre check
        _isAddButtonEnabled.value = !(titleError.value == true || yearError.value == true || directorError.value == true || actorsError.value == true || ratingError.value == true)
    }
}
 */