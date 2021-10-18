package com.android.movies.domain

import com.android.movies.data.api.ApiConfiguration
import com.android.movies.data.api.Movie
import com.android.movies.entities.MovieEntity

internal fun Movie.toMovieEntity(configuration: ApiConfiguration, list: String) = MovieEntity(
    listName = list,
    title = this.title.orEmpty(),
    overview = this.overview.orEmpty(),
    poster_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${this.poster_path}",
    backdrop_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${this.backdrop_path}",
    popularity = this.popularity.orEmpty(),
    id = this.id.orEmpty()
)
