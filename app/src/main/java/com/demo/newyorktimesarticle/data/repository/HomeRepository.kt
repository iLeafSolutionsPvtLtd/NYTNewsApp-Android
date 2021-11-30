package com.demo.newyorktimesarticle.data.repository

import com.demo.newyorktimesarticle.data.model.ArticleResponse
import com.demo.newyorktimesarticle.network.ApiInterface
import com.demo.newyorktimesarticle.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getMostViewedArticles(period: Int, key: String): Flow<State<ArticleResponse>> {
        return flow {
            emit(State.Loading("Loading"))
            val response: Response<ArticleResponse> =
                apiInterface.getMostViewedArticles(period, key)
            if (response.isSuccessful) {
                response.body()?.let { it ->
                    emit(State.Success<ArticleResponse>(it))
                }
            }
            emit(State.Failed(response.errorBody().toString()))
        }.flowOn(Dispatchers.IO)
    }
}
