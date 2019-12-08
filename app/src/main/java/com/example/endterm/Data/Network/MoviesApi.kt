package com.example.endterm.Data.Network

import com.example.endterm.Data.Models.Movie.FavoriteResponse
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Models.Movie.MoviesListResponse
import com.example.endterm.Data.Models.User.LoginResponse
import com.example.endterm.Data.Models.User.ProfileResponse
import com.example.endterm.Data.Models.User.SessionResponse
import com.example.endterm.Data.Models.User.TokenResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface MoviesApi {

    @GET("authentication/token/new")
    fun getToken(): Deferred<TokenResponse>

    @POST("authentication/token/validate_with_login")
    fun login(@Body body: JsonObject): Deferred<LoginResponse>

    @POST("authentication/session/new")
    fun getSession(@Body body: JsonObject): Deferred<SessionResponse>

    @GET("account")
    fun getAccountDetails(@Query("session_id") sessionId: String) : Deferred<ProfileResponse>

    @GET("movie/popular")
    fun getMovies(@Query("page") page: Int): Deferred<Response<MoviesListResponse>>


    @GET("account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Query("page") page: Int,
        @Query("session_id") sessionId: String
    ) : Deferred<Response<MoviesListResponse>>



    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int): Deferred<Response<MovieData>>

    @POST("account/{account_id}/favorite")
    fun markFavorite(
        @Query("session_id") sessionId: String,
        @Body body: JsonObject
    ): Deferred<FavoriteResponse>

}