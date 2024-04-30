package com.example.newsroomtesting.data.remote

import com.example.newsroom.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=2b3350c2e130493a94f280d8c05ca388

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    companion object
    {
        const val API_KEY ="4a16d2ba520046349e233d3d24e9dc30"
        const val BASE_URL = "https://newsapi.org/v2/"

    }

}