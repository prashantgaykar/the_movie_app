package com.prashant.themovieapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**

{
"genres": [
{
"id": 27,
"name": "Horror"
},
{
"id": 9648,
"name": "Mystery"
},
{
"id": 53,
"name": "Thriller"
}
],
}

 */

data class MovieDetails(

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("tagline")
    val tagline: String,

    @Expose
    @SerializedName("overview")
    val overview: String,

    @Expose
    @SerializedName("release_date")
    val releaseDate: String,

    @Expose
    @SerializedName("poster_path")
    val posterPath: String,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,

    @Expose
    @SerializedName("revenue")
    val revenue: Long,

    @Expose
    @SerializedName("runtime")
    val runtime: Int,

    @Expose
    @SerializedName("budget")
    val budget: Long,

    @Expose
    @SerializedName("genres")
    val genres: List<MovieGenre>,


    )