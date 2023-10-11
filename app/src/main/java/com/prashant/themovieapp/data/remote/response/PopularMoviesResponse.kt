package com.prashant.themovieapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.prashant.themovieapp.data.model.PopularMovie

data class PopularMoviesResponse(
    @Expose
    @SerializedName("results")
    val results: List<PopularMovie>
)