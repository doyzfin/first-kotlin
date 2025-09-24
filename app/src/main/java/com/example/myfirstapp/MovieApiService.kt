package com.example.myfirstapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String

    ): Response<MovieResponse>
}

data class MovieResponse(
    val results: List<Movie>
)