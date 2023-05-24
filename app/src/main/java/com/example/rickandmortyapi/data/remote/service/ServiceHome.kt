package com.example.rickandmortyapi.data.remote.service

import com.example.rickandmortyapi.domain.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceHome {
    @GET("api/character")
    suspend fun getCharacter(): Response<Character>

    @GET("api/character/?page={number}")
    suspend fun getCharacterPage(@Query("number") number: Int): Response<Character>
}