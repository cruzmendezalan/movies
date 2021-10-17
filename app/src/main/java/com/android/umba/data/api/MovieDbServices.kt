package com.android.umba.data.api

import com.android.umba.data.configuration.UmbaConfiguration
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieDbServices {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): PopularMovies

    @GET("configuration")
    suspend fun getApiConfiguration(
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): ApiConfiguration
}

data class ApiConfiguration(
    val change_keys: List<String>,
    val images: Images
)

data class Images(
    val backdrop_sizes: List<String>,
    val base_url: String,
    val logo_sizes: List<String>,
    val poster_sizes: List<String>,
    val profile_sizes: List<String>,
    val secure_base_url: String,
    val still_sizes: List<String>
)



data class PopularMovies(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int
)

data class Movie(
    val original_title: String?,
    val overview: String?,
    val title: String?,
    val vote_average: String?,
    val popularity: String?,
    val release_date: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    var imageUrl: String = "",
    val id: String?
)
