package com.prashant.themovieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("name")
    val name: String,
)
