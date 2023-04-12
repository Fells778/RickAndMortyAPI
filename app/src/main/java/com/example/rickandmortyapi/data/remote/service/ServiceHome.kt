package com.example.rickandmortyapi.data.remote.service

import com.example.rickandmortyapi.domain.Character
import retrofit2.Response
import retrofit2.http.GET

interface ServiceHome {
    @GET("api/character")
    suspend fun getCharacter(): Response<Character>
}