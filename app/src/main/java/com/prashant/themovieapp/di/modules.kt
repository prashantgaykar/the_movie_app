package com.prashant.themovieapp.di

import android.content.Context
import com.prashant.themovieapp.BuildConfig
import com.prashant.themovieapp.data.remote.NetworkService
import com.prashant.themovieapp.data.remote.Networking
import com.prashant.themovieapp.data.repository.MovieRepository
import com.prashant.themovieapp.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Singleton
    @Provides
    fun provideNetworkService(
        @ApplicationContext applicationContext: Context,
    ) =
        Networking.create(
            "https://api.themoviedb.org/3/",
            applicationContext.cacheDir,
            5 * 1024 * 1024, // 5MB
        )

    @Provides
    fun provideMovieRepository(
        networkService: NetworkService,
        @TmdbApiKey tmdbApiKey: String
    ): MovieRepository = MovieRepositoryImpl(
        networkService, tmdbApiKey
    )

    @Provides
    @TmdbApiKey
    fun provideTmdbApiKey() = BuildConfig.TMDB_API_KEY
}

