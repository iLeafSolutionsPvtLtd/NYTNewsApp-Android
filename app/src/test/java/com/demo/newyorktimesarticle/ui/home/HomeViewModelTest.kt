package com.demo.newyorktimesarticle.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.newyorktimesarticle.MainCoroutineScopeRule
import com.demo.newyorktimesarticle.data.model.ArticleResponse
import com.demo.newyorktimesarticle.data.model.ResultsItem
import com.demo.newyorktimesarticle.data.repository.HomeRepository
import com.demo.newyorktimesarticle.network.ApiInterface
import com.demo.newyorktimesarticle.utils.State
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest() {
    @Mock
    private lateinit var apiInterface: ApiInterface
    @Mock
    private lateinit var homeViewModel: HomeViewModel
    @Mock
    private lateinit var repository: HomeRepository
    @Mock
    private lateinit var mockObserver: Observer<State<ArticleResponse>>
    @Mock
    private lateinit var mockErrorObserver: Observer<State<ArticleResponse>>
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(repository)
    }

    @Test
    fun onArticleSelected() {
        coroutineScope.runBlockingTest {
            val period = 7
            val key = "0t65wmKB5pMTwuGJF9HEGlEOfjDee0Q0"
            val result = ArticleResponse(
                status = "OK",
                numResults = 20,
                copyright = "Some content",
                results = arrayListOf<ResultsItem>()
            )
            val flow = flow {
                emit(State.Success<ArticleResponse>(result))
            }
            Mockito.`when`(repository.getMostViewedArticles(period, key))
                .thenReturn(flow)
            val liveData = homeViewModel.getArticles()
            liveData.observeForever(mockObserver)
            coroutineScope.advanceTimeBy(10)
            Mockito.verify(mockObserver, Mockito.times(1))
                .onChanged(liveData.value) // onchange has been triggered twice
        } // ktlint-disable no-blank-line-before-rbrace
    }

    @Test()
    fun onError() {
        coroutineScope.runBlockingTest {
            val period = 7
            val key = "0t65wmKB5pMTwuGJF9HEGlEOfjDee0Q0"
            val flow = flow {
                emit(State.Failed<ArticleResponse>("Error"))
            }
            Mockito.`when`(repository.getMostViewedArticles(period, key))
                .thenReturn(flow)

            val liveData = homeViewModel.getArticles()
            liveData.observeForever(mockErrorObserver)
            coroutineScope.advanceTimeBy(10)
            Mockito.verify(mockErrorObserver, Mockito.times(1))
                .onChanged(liveData.value) // onchange has been triggered twice
        }
    }
}
