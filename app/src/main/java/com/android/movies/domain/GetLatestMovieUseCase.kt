package com.android.movies.domain

import com.android.movies.data.api.MovieDbServices
import com.android.movies.entities.MovieDetails
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    private val services: MovieDbServices,
    private val configurationProvider: ApiConfigurationProvider
) {
    suspend operator fun invoke(): MovieDetails {
        val configuration = configurationProvider.getConfiguration()
        val movie = services.getLatestMovie()
        return movie.copy(
            backdrop_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${movie.poster_path}"
        )
    }
}
