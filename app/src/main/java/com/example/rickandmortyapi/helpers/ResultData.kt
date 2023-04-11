package com.example.rickandmortyapi.helpers

sealed class ResultData<out R> {
    data class Success<out T>(val dado: T?) : ResultData<T?>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
}