package com.prashant.themovieapp.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prashant.themovieapp.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object Networking {
    fun create(
        baseUrl: String,
        cacheDir: File,
        cacheSize: Long,
    ): NetworkService {
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                            })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NetworkService::class.java)
    }
}