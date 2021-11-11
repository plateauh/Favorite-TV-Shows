package com.najed.najed.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("search/shows")
    fun getShows (
        @Query("q")
        searchQuery: String
    ): Call<Shows?>?
}