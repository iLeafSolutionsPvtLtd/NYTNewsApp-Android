package com.demo.newyorktimesarticle.utils

sealed class State<T> {
    data class Loading<T>(val message: String) : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failed<T> (val message: String) : State<T>()
}