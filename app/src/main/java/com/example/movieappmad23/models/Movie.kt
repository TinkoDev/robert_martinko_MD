package com.example.movieappmad23.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieappmad23.utils.CustomConverters

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var title: String ="",
    var year: String ="",
    var genre: List<Genre> = listOf(),
    var director: String = "",
    var actors: String = "",
    var plot: String ="",
    val images: List<String> = listOf("https://cdn.maikoapp.com/3d4b/4quqa/150.jpg"),
    var rating: Float = 0f,
    var isFavorite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (title != other.title) return false
        if (year != other.year) return false
        if (genre != other.genre) return false
        if (director != other.director) return false
        if (actors != other.actors) return false
        if (plot != other.plot) return false
        if (images != other.images) return false
        if (rating != other.rating) return false
        if (isFavorite != other.isFavorite) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + year.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + director.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + plot.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + isFavorite.hashCode()
        return result
    }
}

