package demo.lets.work.newsapplication.data.remote.api

import demo.lets.work.newsapplication.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ): NewsDto
}