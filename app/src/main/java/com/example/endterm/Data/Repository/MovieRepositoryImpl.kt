package com.example.endterm.Data.Repository

import com.example.endterm.Data.Models.Movie.FavoriteResponse
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Models.Movie.MoviesListResponse
import com.example.endterm.Data.Network.ApiClient
import com.example.endterm.Domain.Repository.MovieRepository
import com.google.gson.JsonObject

class MovieRepositoryImpl : MovieRepository{
    override suspend fun getPopularMovies(page: Int): MoviesListResponse? {
        return ApiClient.apiClient.getMovies(page).await().body()
    }

    override suspend fun getMovie(id: Int): MovieData? =
        ApiClient.apiClient.getMovie(id).await().body()

    override suspend fun markFavorite(movieId: Int, sessionId: String, favorite: Boolean): FavoriteResponse? {
        val body = JsonObject().apply {
            addProperty("media_type", "movie")
            addProperty("media_id", movieId)
            addProperty("favorite", favorite)
        }
        return ApiClient.apiClient.markFavorite(sessionId, body).await()
    }

    override suspend fun getFavoriteMovies(page: Int, sessionId: String): MoviesListResponse? {
        return ApiClient.apiClient.getFavoriteMovies(page, sessionId).await().body()
    }
}