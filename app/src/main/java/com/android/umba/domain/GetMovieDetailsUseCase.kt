package com.android.umba.domain

import com.android.umba.data.api.MovieDbServices
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

