package com.example.endterm.Domain.Repository

import com.example.endterm.Data.Models.Movie.FavoriteResponse
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Models.Movie.MoviesListResponse
import com.example.endterm.Presentation.Movie.Profile.Profile


interface MovieRepository{
    suspend fun getPopularMovies(page: Int): MoviesListResponse?

    suspend fun getMovie(id: Int): MovieData?

    suspend fun markFavorite(movieId: Int, sessionId: String, favorite: Boolean): FavoriteResponse?

    suspend fun getFavoriteMovies(page: Int, sessionId: String): MoviesListResponse?


}
