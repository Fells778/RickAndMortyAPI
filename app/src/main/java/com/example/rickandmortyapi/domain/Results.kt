package com.example.rickandmortyapi.domain

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("id")
    val id: Int,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("image")
    val image: String,
):java.io.Serializable
