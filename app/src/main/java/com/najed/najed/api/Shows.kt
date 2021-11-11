package com.najed.najed.api

import com.google.gson.annotations.SerializedName

class Shows: ArrayList<ShowsItem>()

data class ShowsItem (
    @SerializedName("show")
    val show: Show
)

data class Show (
    @SerializedName("image")
    val image: Image,

    @SerializedName("language")
    val language: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("summary")
    val summary: String
)

data class Image (
    @SerializedName("medium")
    val medium: String
)