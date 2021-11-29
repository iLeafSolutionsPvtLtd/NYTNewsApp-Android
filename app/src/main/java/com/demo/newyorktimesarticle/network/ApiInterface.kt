package com.demo.newyorktimesarticle.network

import com.demo.newyorktimesarticle.data.model.ArticleResponse
import com.demo.newyorktimesarticle.utils.Constants

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(Constants.API_MOST_POPULAR)
    suspend fun getMostViewedArticles(
        @Path("period") period: Int,
        @Query("api-key") key: String
    ): Response<ArticleResponse>
}
