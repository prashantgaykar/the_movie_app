package com.prashant.themovieapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("MovieApp", "Started.....")
    }
}