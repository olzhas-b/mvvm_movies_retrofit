package com.example.endterm.Data.Models.Movie

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id") val id : Int?=null,
    @SerializedName("title") val title : String?=null,
    @SerializedName("vote_average") val vote_average : Float?=null,
    @SerializedName("backdrop_path") val backdropImg : String?=null,
    @SerializedName("poster_path") val posterImg : String?=null,
    @SerializedName("overview") val overview: String? = null
)