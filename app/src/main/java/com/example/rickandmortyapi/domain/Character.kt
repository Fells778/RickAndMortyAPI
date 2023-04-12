package com.example.rickandmortyapi.domain

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val result: List<Results>
):java.io.Serializable




