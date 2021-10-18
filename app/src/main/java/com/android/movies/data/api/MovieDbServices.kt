package com.android.movies.data.api

import com.android.movies.data.configuration.UmbaConfiguration
import com.android.movies.entities.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDbServices {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): MoviesListResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): MoviesListResponse

    @GET("configuration")
    suspend fun getApiConfiguration(
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): ApiConfiguration

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): MovieDetails


    @GET("movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key") apiKey: String = UmbaConfiguration.getApiKey()
    ): MovieDetails
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


data class MoviesListResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int
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
