package com.demo.newyorktimesarticle.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.newyorktimesarticle.data.model.ArticleResponse
import com.demo.newyorktimesarticle.data.repository.HomeRepository
import com.demo.newyorktimesarticle.utils.Constants
import com.demo.newyorktimesarticle.utils.State

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    lateinit var response: Flow<State<ArticleResponse>>

    fun getArticles() {
        viewModelScope.launch {
            response = repository.getMostViewedArticles(7, Constants.API_KEY)
        }
    }
}
