package com.example.randtronomy.services.network

import com.example.randtronomy.services.model.AstronomyItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyServices {
    @GET("planetary/apod")
    suspend fun getRandom(
        @Query("api_key") apiKey : String,
        @Query("count") count : Int = 1,
    ) : ArrayList<AstronomyItem>
}