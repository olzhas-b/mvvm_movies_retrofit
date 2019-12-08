package com.example.endterm.Data.Network

import android.util.Log
import com.example.endterm.Utils.Constant
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val apiClient: MoviesApi by lazy {
        val logging = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message)}
        ).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor {chain ->
                val newUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key",
                        Constant.API_KEY
                    )
                    .build()
                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return@lazy retrofit.create(MoviesApi::class.java)
    }
}