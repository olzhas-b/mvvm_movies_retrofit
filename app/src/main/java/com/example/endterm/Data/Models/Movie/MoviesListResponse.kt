package com.example.endterm.Data.Models.Movie

import com.example.endterm.Data.Models.Movie.MovieData
import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")  val page : Int?,
    @SerializedName("total_pages")  val total_pages : Int?,
    @SerializedName("total_results")  val total_results : Int?,
    @SerializedName("results")  val results : List<MovieData>?
)