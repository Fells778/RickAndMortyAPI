package com.example.rickandmortyapi.domain

data class Info(
    val count: Int,
    val next: String? = null,
    val pages: Int,
    val prev: Any? = null
):java.io.Serializable
