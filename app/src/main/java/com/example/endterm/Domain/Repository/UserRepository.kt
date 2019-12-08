package com.example.endterm.Domain.Repository

import com.example.endterm.Data.Models.User.LoginResponse
import com.example.endterm.Data.Models.User.ProfileResponse
import com.example.endterm.Data.Models.User.SessionResponse
import com.example.endterm.Data.Models.User.TokenResponse


interface UserRepository {
    suspend fun createToken(): TokenResponse

    suspend fun login(username: String, password: String, requestToken: String): LoginResponse

    suspend fun createSession(requestToken: String): SessionResponse

    suspend fun getProfileInformation(sessionId: String): ProfileResponse?
}