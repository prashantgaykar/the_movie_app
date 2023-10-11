package com.prashant.themovieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMovie(

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("release_date")
    val releaseDate: String,

    @Expose
    @SerializedName("poster_path")
    val posterPath: String,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,
)
