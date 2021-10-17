package com.android.umba.domain

import com.android.umba.data.api.ApiConfiguration
import com.android.umba.data.api.Movie

internal fun Movie.toMovieEntity(configuration: ApiConfiguration) = MovieEntity(
    listName = POPULAR_MOVIES_LIST,
    title = this.title.orEmpty(),
    overview = this.overview.orEmpty(),
    poster_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${this.poster_path}",
    backdrop_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${this.backdrop_path}",
    popularity = this.popularity.orEmpty(),
    id = this.id.orEmpty()
)
