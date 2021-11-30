package com.demo.newyorktimesarticle.ui.home

import androidx.lifecycle.*
import com.demo.newyorktimesarticle.data.model.ArticleResponse
import com.demo.newyorktimesarticle.data.repository.HomeRepository
import com.demo.newyorktimesarticle.utils.Constants
import com.demo.newyorktimesarticle.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    fun getArticles(): LiveData<State<ArticleResponse>> {
        return repository.getMostViewedArticles(7, Constants.API_KEY).asLiveData()
    }
}
