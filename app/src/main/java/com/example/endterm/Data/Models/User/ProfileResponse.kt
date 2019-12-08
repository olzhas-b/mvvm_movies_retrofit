package com.example.endterm.Data.Models.User

import com.google.gson.annotations.SerializedName

data class ProfileResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?
)
