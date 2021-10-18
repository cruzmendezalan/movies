package com.android.movies.domain

import com.android.movies.data.api.MovieDbServices
import com.android.movies.entities.MovieDetails
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val services: MovieDbServices,
    private val configurationApi: ApiConfigurationProvider
) {
    suspend operator fun invoke(movieId: String): MovieDetails {
        val configuration = configurationApi.getConfiguration()
        val movieDetails = getMovieDetails(movieId)

        return movieDetails.copy(
            backdrop_path = "${configuration.images.secure_base_url}${configuration.images.still_sizes.last()}${movieDetails.backdrop_path}"
        )
    }

    private suspend fun getMovieDetails(movieId: String): MovieDetails {
        return services.getMovieDetails(movieId)
    }
}

