package com.android.umba.domain

import com.android.umba.data.api.MovieDbServices
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
