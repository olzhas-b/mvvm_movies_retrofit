package com.example.endterm.Data.Repository

import com.example.endterm.Data.Models.User.LoginResponse
import com.example.endterm.Data.Models.User.ProfileResponse
import com.example.endterm.Data.Models.User.SessionResponse
import com.example.endterm.Data.Models.User.TokenResponse
import com.example.endterm.Data.Network.ApiClient
import com.example.endterm.Domain.Repository.UserRepository
import com.google.gson.JsonObject
import retrofit2.Response

class UserRepositoryImpl : UserRepository {
    override suspend fun createToken(): TokenResponse =
        ApiClient.apiClient.getToken().await()

    override suspend fun login(username: String, password: String, requestToken: String): LoginResponse {
        val body = JsonObject().apply {
            addProperty("username", username)
            addProperty("password", password)
            addProperty("request_token", requestToken)
        }
        return ApiClient.apiClient.login(body).await()
    }

    override suspend fun createSession(requestToken: String): SessionResponse {
        val body = JsonObject().apply {
            addProperty("request_token", requestToken)
        }
        return ApiClient.apiClient.getSession(body).await()
    }

    override suspend fun getProfileInformation(sessionId: String): ProfileResponse? {
        return ApiClient.apiClient.getAccountDetails(sessionId).await()
    }


}